package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.dto.HumanitarianAidDTO;
import com.edanrh.apiong.dto.converts.HumanitarianAidDTOConvert;
import com.edanrh.apiong.exceptions.BussinesRuleException;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.exceptions.QuantityEntityException;
import com.edanrh.apiong.repository.HeadquarterRepository;
import com.edanrh.apiong.repository.HumanitarianAidRepository;
import com.edanrh.apiong.repository.ProfessionRepository;
import com.edanrh.apiong.repository.SanitaryRepository;
import com.edanrh.apiong.repository.entities.*;
import com.edanrh.apiong.service.HumanitarianAidService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HumanitarianAidServiceImpl implements HumanitarianAidService {

    private HumanitarianAidRepository humanitarianAidRepository;
    private ProfessionRepository professionRepository;
    private HeadquarterRepository headquarterRepository;
    private SanitaryRepository sanitaryRepository;
    private HumanitarianAidDTOConvert dtoConvert;

    @Override
    public List<HumanitarianAidDTO> findAll() throws ContentNullException {
        List<HumanitarianAid> entities = (List<HumanitarianAid>) humanitarianAidRepository.findAll();
        if (entities.isEmpty()) {
            throw new ContentNullException("code", "There isn't humanitarian aid data", HttpStatus.NO_CONTENT);
        }else {
            List<HumanitarianAidDTO> resultDTO = new ArrayList<>();
            for (HumanitarianAid aid : entities){
                resultDTO.add(dtoConvert.toDTO(aid));
            }
            return resultDTO;
        }
    }

    @Override
    public HumanitarianAidDTO save(HumanitarianAidDTO humanitarianAidDTO, Shipment shipment) throws
            NotFoundException, ContentNullException, QuantityEntityException, BussinesRuleException {
        Optional<Headquarter> head = headquarterRepository.findByCodeHq(humanitarianAidDTO.getCodeHq());
        Optional<Profession> profession = professionRepository.findByCodePr(humanitarianAidDTO.getCodePr());
        if (head.isEmpty()){
            throw new NotFoundException("code", "Headquarter not found", HttpStatus.NOT_FOUND);
        } else if (profession.isEmpty()) {
            throw new NotFoundException("code", "Profession not found", HttpStatus.NOT_FOUND);
        }else {
            HumanitarianAid entity = dtoConvert.toEntity(humanitarianAidDTO);
            for (Long doc : humanitarianAidDTO.getDocuments()){
                Optional<Sanitary> aid = sanitaryRepository.findByDocument(doc);
                if (aid.isEmpty()){
                    throw new NotFoundException("code", "Doesn't exist a sanitary with document: " + doc, HttpStatus.NOT_FOUND);
                } else if (!aid.get().getIsAvailable()) {
                    throw new BussinesRuleException("code", "Sanitary: " + doc + " isn't available", HttpStatus.BAD_REQUEST);
                } else if (!aid.get().getProfession().getCodePr().equals(profession.get().getCodePr())) {
                    throw new BussinesRuleException("code", "Sanitary: " + doc + " doesn't participate in the profession", HttpStatus.BAD_REQUEST);
                } else if (!aid.get().getHeadquarter().getCodeHq().equals(head.get().getCodeHq())) {
                        throw new BussinesRuleException("code", "Sanitary: " + doc + " doesn't belong at the headquarter", HttpStatus.BAD_REQUEST);
                } else {
                    entity.getDocumentsSanitaries().add(new DocSanitarySent(doc));
                    aid.get().addParticipation();
                    aid.get().setIsAvailable(false);
                    sanitaryRepository.save(aid.get());
                }
            }
            entity.setHeadquarter(head.get());
            entity.setProfession(profession.get());
            entity.setShipment(shipment);
            entity.setQuantity(entity.getDocumentsSanitaries().size());
            return dtoConvert.toDTO(humanitarianAidRepository.save(entity));
        }
    }
}

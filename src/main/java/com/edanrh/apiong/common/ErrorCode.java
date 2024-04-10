package com.edanrh.apiong.common;

public final class ErrorCode {

    // Set data
    public static final String EMAIL_VALIDATION = "EM-VAL";
    public static final String EMAIL_AVAILABLE = "EM-AVL";
    public static final String DATE_VALIDATION = "DTE-VAL";

    // Administrative Entity
    public static final String ADMIN_CONTENT_NOT_FOUND = "ADM-CNF";
    public static final String ADMIN_DOCUMENT_NOT_FOUND = "ADM-DNF";
    public static final String ADMIN_DUPLICATE_CREATION = "ADM-DUP";

    // AnnualFee Entity
    public static final String ANNUAL_FEE_CONTENT_NOT_FOUND = "AF-CNF";
    public static final String ANNUAL_FEE_NAME_NOT_FOUND = "AF-DNF";
    public static final String ANNUAL_FEE_DUPLICATE_CREATION = "AF-DUP";
    public static final String ANNUAL_FEE_REFERENCED_EXIST = "AF-REF";

    // City Entity
    public static final String CITY_CONTENT_NOT_FOUND = "CTY-CNF";
    public static final String CITY_NAME_NOT_FOUND = "CTY-NNF";
    public static final String CITY_DUPLICATE_CREATION = "CTY-DUP";
    public static final String CITY_REFERENCED_EXIST = "CTY-REF";

    // Person Entity
    public static final String PERSON_DUPLICATE_CREATION = "PER-DUP";

    // Director Entity
    public static final String DIRECTOR_CONTENT_NOT_FOUND = "DIR-CNF";
    public static final String DIRECTOR_DOCUMENT_NOT_FOUND = "DIR-DNF";

    // Headquarter Entity
    public static final String HEADQUARTER_CONTENT_NOT_FOUND = "HQ-CNF";
    public static final String HEADQUARTER_CODE_NOT_FOUND = "HQ-CNF";
    public static final String HEADQUARTER_DUPLICATE_CREATION = "HQ-DUP";
    public static final String HEADQUARTER_OCCUPIED = "HQ-BRV";
    public static final String HEADQUARTER_REFERENCE_EXISTING = "HQ-REF";

    // HumanAid Entity
    public static final String HUMAN_AID_CONTENT_NOT_FOUND = "HA-CNF";

    // MaterialAid Entity
    public static final String MATERIAL_AID_CONTENT_NOT_FOUND = "MA-CNF";

    // Partner Entity
    public static final String PARTNER_CONTENT_NOT_FOUND = "PTNR-CNF";
    public static final String PARTNER_DOCUMENT_NOT_FOUND = "PTNR-DNF";

    // Profession Entity
    public static final String PROFESSION_CONTENT_NOT_FOUND = "PRO-CNF";
    public static final String PROFESSION_CODE_NOT_FOUND = "PRO-CNF";
    public static final String PROFESSION_DUPLICATE_CREATION = "PRO-DUP";

    // Sanitary Entity
    public static final String SANITARY_CONTENT_NOT_FOUND = "SAN-CNF";
    public static final String SANITARY_DOCUMENT_NOT_FOUND = "SAN-DNF";
    public static final String SANITARY_AVAILABLE = "SAN-AVL";
    public static final String SANITARY_PROFESSION = "SAN-PRO";
    public static final String SANITARY_BELONGS_HEAD = "SAN-BHQ";

    // Shelter Entity
    public static final String SHELTER_CONTENT_NOT_FOUND = "SHT-CNF";
    public static final String SHELTER_CODE_NOT_FOUND = "SHT-CNF";
    public static final String SHELTER_REFERENCE_EXISTING = "SHT-REF";

    // Shipment Entity
    public static final String SHIPMENT_CONTENT_NOT_FOUND = "SHP-CNF";
    public static final String SHIPMENT_CODE_NOT_FOUND = "SHP-CNF";
    // User Entity
    public static final String USER_CONTENT_NOT_FOUND = "USR-CNF";
    public static final String USER_EMAIL_NOT_FOUND = "USR-ENF";
    public static final String USER_DUPLICATE_CREATION = "USR-DUP";
    public static final String USER_ROLE_VALIDATION = "USR-VLD";

}

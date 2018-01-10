package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.Scope;
import configuration.Token;
import data.*;
import exception.JsonException;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * This component map to Bean from Map about user data
 */
@Component
public class Mapper {

    @Autowired
    private EsiaUser user;

    /**
     * Map user data
     * @param map
     */
    public void mapUserDataFromMap(Map<String, Object> map) {
        user.setCitizenship((String) map.get("citizenship"));
        user.setName((String) map.get("firstName"));
        user.setSurName((String) map.get("lastName"));
        user.setMiddleName((String) map.get("middleName"));
        LocalDateTime timeBirth = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        Long sec = (Long) map.get("birthDate");
        user.setBirthDate(timeBirth.plusSeconds(sec));
        user.setBirthPlace((String) map.get("birthPlace"));
        user.setGender((String) map.get("gender"));
        user.setTrusted((Boolean) map.get("trusted"));
        user.setSnils((Long) map.get("snils"));
        user.setInn((Long) map.get("inn"));
        LocalDateTime timeUpdate = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        sec = (Long) map.get("updatedOn");
        user.setUpdatedOn(timeUpdate.plusSeconds(sec));
    }

    /**
     * Map other user data according to scope
     * @param map
     * @param scope
     */
    public void mapOtherUserDateFromMap(Map<String, Object> map, String scope) {
        switch (scope) {
            case Scope.DOCS:
                mapDocumentFromMap(map);
                break;
            case Scope.ORGS:
                mapOrganizationFromMap(map);
                break;
            case Scope.VHLS:
                mapVehicleFromMap(map);
                break;
            case Scope.KIDS:
                mapChildFromMap(map);
                break;
            case Scope.CTTS:
                mapContactsFromMap(map);
                break;
            case Scope.ADDRS:
                mapAddressFromMap(map);
                break;
        }
    }

    private void mapAddressFromMap(Map<String, Object> map) {
        Address address = new Address();
        address.setAdditionArea((String) map.get("additionArea"));
        address.setAdditionAreaStreet((String) map.get("additionAreaStreet"));
        address.setArea((String) map.get("area"));
        address.setAddressStr((String) map.get("addressStr"));
        address.setCity((String) map.get("city"));
        address.setCountryId((String) map.get("countryId"));
        address.setBuilding((String) map.get("building"));
        address.setDistrict((String) map.get("district"));
        address.setFiasCode((String) map.get("fiasCode"));
        address.setFlat((String) map.get("flat"));
        address.setFrame((String) map.get("frame"));
        address.setHouse((String) map.get("house"));
        address.setRegion((String) map.get("region"));
        address.setSettlement((String) map.get("settlement"));
        address.setStreet((String) map.get("street"));
        address.setType((String) map.get("type"));
        address.setZipCode((String) map.get("zipCode"));
        user.addAddress(address);
    }

    private void mapDocumentFromMap(Map<String, Object> map) {
        Document document = new Document();
        document.setNumber((String) map.get("number"));
        document.setSeries((String) map.get("series"));
        document.setType((String) map.get("type"));
        document.setVrfStu((String) map.get("vrfStu"));
        LocalDateTime time = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        Long sec = (Long) map.get("expiryDate");
        document.setExpiryDate(time.plusSeconds(sec));
        document.setFirstName((String) map.get("firstName"));
        LocalDateTime time2 = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        sec = (Long) map.get("issueDate");
        document.setIssueDate(time2.plusSeconds(sec));
        document.setIssuedBy((String) map.get("issuedBy"));
        document.setIssueId((String) map.get("issueId"));
        document.setLastName((String) map.get("lastName"));
        user.addDocuments(document);
    }

    private void mapOrganizationFromMap(Map<String, Object> map) {
        Organization organization = new Organization();
        organization.setInn((Long) map.get("inn"));
        organization.setKpp((Long) map.get("kpp"));
        organization.setLeg((Long) map.get("leg"));
        organization.setOgrn((Long) map.get("ogrn"));
        organization.setType((String) map.get("type"));
        organization.setFullName((String) map.get("fullName"));
        organization.setShortName((String) map.get("shortName"));
        user.addOrganization(organization);
    }

    private void mapVehicleFromMap(Map<String, Object> map) {
        Vehicle vehicle = new Vehicle();
        vehicle.setName((String) map.get("name"));
        vehicle.setNumberPlate((String) map.get("numberPlate"));
        Vehicle.RegCertificate reg = new Vehicle.RegCertificate();
        reg.setNumber((String) map.get("number"));
        reg.setSeries((String) map.get("series"));
        vehicle.setRegCertificate(reg);
        user.addVehicle(vehicle);
    }

    private void mapChildFromMap(Map<String, Object> map) {
        Child child = new Child();
        LocalDateTime time = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        Long sec = (Long) map.get("birthDate");
        child.setBirthDate(time.plusSeconds(sec));
        child.setGender((String) map.get("gender"));
        child.setInn((Long) map.get("gender"));
        child.setSnils((Long) map.get("gender"));
        child.setMiddleName((String) map.get("middleName"));
        child.setTrusted((Boolean) map.get("trusted"));
        LocalDateTime time2 = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        sec = (Long) map.get("updatedOn");
        child.setUpdatedOn(time2.plusSeconds(sec));
        child.setFirstName((String) map.get("firstName"));
        child.setLastName((String) map.get("lastName"));
        //child.setDocuments((String) map.get("gender"));
        user.addChild(child);
    }

    private void mapContactsFromMap(Map<String, Object> map) {
        user.addContact(new Contact(
                (String) map.get("type"),
                (String) map.get("vrfStu"),
                (String) map.get("value")
        ));
    }

    public void mapTokenDataFromMap(OAuthJSONAccessTokenResponse oauthResponse, Token token) throws JsonException {
        token.setAccessToken(oauthResponse.getAccessToken());
        token.setExpiresIn(oauthResponse.getExpiresIn());
        token.setRefreshToken(oauthResponse.getRefreshToken());
        token.setScope(oauthResponse.getScope());
        token.setTokenType(oauthResponse.getTokenType());
        String[] accessParts = oauthResponse.getAccessToken().split("\\.");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> info; //pare json - only token info
        try {
            info = mapper.readValue(new String(Base64.getUrlDecoder().decode(accessParts[1]), "UTF-8"),
                    new TypeReference<Map<String, String>>() {
                    });
        } catch (IOException e) {
            throw new JsonException(e);
        }
        token.setExp(info.get("exp"));
        token.setIss(info.get("iss"));
        token.setNbf(info.get("nbf"));
        token.setSid(info.get("urn:esia:sid"));
        token.setSbjId(info.get("urn:esia:sbj_id"));
        token.setClientId(info.get("client_id"));
        token.setIat(info.get("iat"));
    }

}

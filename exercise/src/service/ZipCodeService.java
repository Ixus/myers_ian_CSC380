package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService(name = "ZipCodeService", targetNamespace = "http://localhost/ZipCodeService")
public interface ZipCodeService {

    @WebMethod(operationName = "getState")
    public @WebResult(name = "State")
    State getState(@WebParam(name = "zipCode") Integer zipCode);

    @WebMethod(operationName = "addZipCode")
    public void addZipCode(@WebParam(name = "State") State state, @WebParam(name = "zipCodes") List<Integer> zipCodes);
}

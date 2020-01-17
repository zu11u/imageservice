package kz.minjust.ezags.archive;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface SoapArchImage {

    @WebMethod
    @WebResult(name="AltelResult")
    IResponse GetImage(IRequest req);
}


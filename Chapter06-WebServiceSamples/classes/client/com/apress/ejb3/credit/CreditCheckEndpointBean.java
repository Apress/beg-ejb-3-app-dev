
package com.apress.ejb3.credit;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.0_03-b18-fcs
 * Generated source version: 2.0
 * 
 */
@WebService(name = "CreditCheckEndpointBean", targetNamespace = "http://www.apress.com/ejb3/credit")
public interface CreditCheckEndpointBean {


    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod(operationName = "CreditCheck")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "CreditCheck", targetNamespace = "http://www.apress.com/ejb3/credit", className = "com.apress.ejb3.credit.CreditCheck")
    @ResponseWrapper(localName = "CreditCheckResponse", targetNamespace = "http://www.apress.com/ejb3/credit", className = "com.apress.ejb3.credit.CreditCheckResponse")
    public boolean creditCheck(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}

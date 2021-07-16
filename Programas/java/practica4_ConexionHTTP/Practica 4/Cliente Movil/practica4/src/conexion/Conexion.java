package conexion;

import javax.microedition.io.*;
import java.io.*;

/**
 * @author  Ing. Francisco Martinez
 * @version
 */
public class Conexion {

  private HttpConnection http;
  private InputStream iStrm;
  private OutputStream oStrm;
  private String errorMsg;
  private String url;

  public Conexion(String _url) {
    http = null;
    iStrm = null;
    errorMsg = null;
    url = _url;
  }

  /*--------------------------------------------------
   * Access servlet using GET
   *-------------------------------------------------*/
  public String sendWithGET(String[] parameters) {

    String ret = null;
    // Data is passed at the end of url for GET
    StringBuffer urlGET = new StringBuffer(url);
    urlGET.append("?");
    urlGET.append(parameters[0]);
    for (int i = 1; i < parameters.length; i++) {
      urlGET.append("&");
      urlGET.append(parameters[i]);
    }

    try {
      http = (HttpConnection) Connector.open(urlGET.toString());
      //----------------
      // Client Request
      //----------------
      // 1) Send request method
      http.setRequestMethod(HttpConnection.GET);
      // 2) Send header information - none
      // 3) Send body/data - data is at the end of URL
      //----------------
      // Server Response
      //----------------
      iStrm = http.openInputStream();
      // Three steps are processed in this method call
      ret = processServerResponse(http, iStrm);
    }
    catch (IOException e) {
      e.printStackTrace();
      errorMsg="Don't Connection";
    }
    finally {
      try {
        // Clean up
        if (iStrm != null) {
          iStrm.close();
        }
        if (http != null) {
          http.close();
        }
      }
      catch (IOException e) {
        e.printStackTrace();
        errorMsg="Don't Streams";
      }

    }
    return ret;
  }

  /*--------------------------------------------------
   * Access servlet using POST
   *-------------------------------------------------*/
  public String sendWithPOST(String[] parameters) throws IOException {

    String ret = null;

    try {
      http = (HttpConnection) Connector.open(url);
      oStrm = http.openOutputStream();
      //----------------
      // Client Request
      //----------------
      // 1) Send request type
      http.setRequestMethod(HttpConnection.POST);
      // 2) Send header information. Required for POST to work!
      http.setRequestProperty("Content-Type",
                              "application/x-www-form-urlencoded");
      http.setRequestProperty("User-Agent",
                              "Profile/MIDP-2.0 Configuration/CLDC-1.1");
      // If you experience connection/IO problems, try
      // removing the comment from the following line
      //http.setRequestProperty("Connection", "close");
      // 3) Send data/body
      // Write account number
      byte data[] = parameters[0].getBytes();
      oStrm.write(data);

      for (int i = 1; i < parameters.length; i++) {
        data = ("&" + parameters[i]).getBytes();
        oStrm.write(data);
      }

      //----------------
      // Server Response
      //----------------
      iStrm = http.openInputStream();
      // Three steps are processed in this method call
      ret = processServerResponse(http, iStrm);
    }
    finally {
      // Clean up
      if (iStrm != null) {
        iStrm.close();
      }
      if (oStrm != null) {
        oStrm.close();
      }
      if (http != null) {
        http.close();
      }
    }
    return ret;
  }

  /*--------------------------------------------------
   * Process a response from a server
   *-------------------------------------------------*/
  private String processServerResponse(HttpConnection http,
                                       InputStream iStrm) throws IOException {
    String str = null;
    // 1) Get status Line
    if (http.getResponseCode() == HttpConnection.HTTP_OK) {
      // 2) Get header information - none
      // 3) Get body (data)
      int length = (int) http.getLength();

      if (length != -1) {
        byte servletData[] = new byte[length];
        iStrm.read(servletData);
        str = new String(servletData);
      }
      else { // Length not available...
        ByteArrayOutputStream bStrm = new ByteArrayOutputStream();
        int ch;
        while ( (ch = iStrm.read()) != -1) {
          bStrm.write(ch);
        }
        str = new String(bStrm.toByteArray());
        bStrm.close();
      }

    }
    else {

      // Use message from the servlet
      errorMsg = new String(http.getResponseMessage());
    }
    return str;
  }

  public String getErrorMessage() {
    return errorMsg;
  }

}

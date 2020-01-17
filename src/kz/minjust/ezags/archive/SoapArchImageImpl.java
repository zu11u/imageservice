package kz.minjust.ezags.archive;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.codec.binary.Base64;

@WebService
public class SoapArchImageImpl implements SoapArchImage {

    public IResponse GetImage(IRequest req) {
        IResponse iresponse = new IResponse();
        iresponse.setActId(req.getActId());

        setImages(iresponse);

        return iresponse;
    }

    private void setImages(IResponse res) {
        Connection con = getConnection();
        if (con != null) {
            PreparedStatement sttm = null;
            ResultSet rs = null;

            String q = "select * from import_scans_test where zags_id=?";

            try {
                sttm = con.prepareStatement(q);
                sttm.setString(1, res.getActId());
                rs = sttm.executeQuery();
                if (rs.next()) {
                    if (rs.getBlob("image1") != null) {
                        Blob blob1 = rs.getBlob("image1");
                        byte[] bdata1 = blob1.getBytes(1, (int) blob1.length());
                        res.setImage1(Base64.encodeBase64String(bdata1));
                    }
                    if (rs.getBlob("image2") != null) {
                        Blob blob2 = rs.getBlob("image2");
                        byte[] bdata2 = blob2.getBytes(1, (int) blob2.length());
                        res.setImage2(Base64.encodeBase64String(bdata2));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null)
                        rs.close();
                    if (sttm != null)
                        sttm.close();
                    if (con != null)
                        con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Connection getConnection() {
        Connection con = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ezags-local");
            con = ds.getConnection();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return con;
    }

}

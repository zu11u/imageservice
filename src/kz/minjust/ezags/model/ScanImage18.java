package kz.minjust.ezags.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="import_scans", schema="ZAGS_2018")
public class ScanImage18 extends ScanImage implements Serializable
{
    @Id
    @Column(name="zags_id")
    private String zagsId;
    @Column(name="image1")
    private byte[] image1;
    @Column(name="image2")
    private byte[] image2;

    public byte[] getImage1()
    {
        return this.image1;
    }
    public byte[] getImage2()
    {
        return this.image2;
    }


}

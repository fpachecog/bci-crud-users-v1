package cl.bci.api.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
*
* @author francisco
*/
@Entity
@Table(name = "phones")
@XmlRootElement
public class Phones implements Serializable{

	private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "number")
    private String number;
    @Basic(optional = false)
    @Column(name = "citycode")
    private String cityCode;
    @Basic(optional = false)
    @Column(name = "contrycode")
    private String contryCode;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;
    
    public Phones() {
    	
    }
    
    public Phones(String number) {
    	this.number = number;
    }

	public Phones(String number, String cityCode, String contryCode, Users userId) {
		this.number = number;
		this.cityCode = cityCode;
		this.contryCode = contryCode;
		this.userId = userId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getContryCode() {
		return contryCode;
	}

	public void setContryCode(String contryCode) {
		this.contryCode = contryCode;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}
    
	
    
    
}

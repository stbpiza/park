package DTO;

public class receiptDTO {
	private String rec_id;
	private String pay_time;
	private String pay_price;
	private String regular_non;
	private String gate_id;
	
	public String getRec_id() {
		return rec_id;
	}
	public void setRec_id(String rec_id) {
		this.rec_id = rec_id;
	}
	public String getPay_time() {
		return pay_time;
	}
	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}
	public String getPay_price() {
		return pay_price;
	}
	public void setPay_price(String pay_price) {
		this.pay_price = pay_price;
	}
	public String getRegular_non() {
		return regular_non;
	}
	public void setRegular_non(String regular_non) {
		this.regular_non = regular_non;
	}
	public String getGate_id() {
		return gate_id;
	}
	public void setGate_id(String gate_id) {
		this.gate_id = gate_id;
	}
	
}

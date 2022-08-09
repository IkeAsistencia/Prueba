package com.ike.service.banbajio.registerAttendanceUsage.dto;

import java.io.Serializable;

public class ResultRegisterAttendance implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer folioServicio;
	private String clAfiltmk;

	public ResultRegisterAttendance() {

	}

	public ResultRegisterAttendance(Integer folioServicio, String clAfiltmk) {
		this.folioServicio = folioServicio;
		this.clAfiltmk = clAfiltmk;
	}

	public Integer getFolioServicio() {
		return folioServicio;
	}

	public void setFolioServicio(Integer folioServicio) {
		this.folioServicio = folioServicio;
	}

	public String getClAfiltmk() {
		return clAfiltmk;
	}

	public void setClAfiltmk(String clAfiltmk) {
		this.clAfiltmk = clAfiltmk;
	}

	@Override
	public String toString() {
		return "ResultRegisterAttendance [folioServicio=" + folioServicio + ", clAfiltmk=" + clAfiltmk + "]";
	}

}

package com.ike.service.banbajio.cancellationService.dto;

import java.io.Serializable;

public class ResultCancellationService implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer folioCancelacion;
	private Integer clAfiltmk;

	public ResultCancellationService() {

	}

	public ResultCancellationService(Integer folioCancelacion, Integer clAfiltmk) {
		this.folioCancelacion = folioCancelacion;
		this.clAfiltmk = clAfiltmk;
	}

	public Integer getFolioCancelacion() {
		return folioCancelacion;
	}

	public void setFolioCancelacion(Integer folioCancelacion) {
		this.folioCancelacion = folioCancelacion;
	}

	public Integer getClAfiltmk() {
		return clAfiltmk;
	}

	public void setClAfiltmk(Integer clAfiltmk) {
		this.clAfiltmk = clAfiltmk;
	}

	@Override
	public String toString() {
		return "ResultCancellationService [folioCancelacion=" + folioCancelacion + ", clAfiltmk=" + clAfiltmk + "]";
	}

}

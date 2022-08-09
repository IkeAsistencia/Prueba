package com.ike.service.banbajio.getCustomersProspects.dto;

import java.util.List;

public class ResultProspects {

	private Integer noClienteUnico;
	private String nombre;
	private boolean empleado;
	private String genero;
	private String email;
	private String rfc;
	private String sucursal;
	private String estado;
	private String fechaNacimiento;
	private String telefonoCasa;
	private String telefonoOficina;
	private String telefonoCelular;
	private String fechaAlta;
	private List<CardProspects> tarjetas;

	public Integer getNoClienteUnico() {
		return noClienteUnico;
	}

	public void setNoClienteUnico(Integer noClienteUnico) {
		this.noClienteUnico = noClienteUnico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isEmpleado() {
		return empleado;
	}

	public void setEmpleado(boolean empleado) {
		this.empleado = empleado;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefonoCasa() {
		return telefonoCasa;
	}

	public void setTelefonoCasa(String telefonoCasa) {
		this.telefonoCasa = telefonoCasa;
	}

	public String getTelefonoOficina() {
		return telefonoOficina;
	}

	public void setTelefonoOficina(String telefonoOficina) {
		this.telefonoOficina = telefonoOficina;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public List<CardProspects> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<CardProspects> tarjetas) {
		this.tarjetas = tarjetas;
	}

	@Override
	public String toString() {
		return "ResultProspects [email=" + email + ", empleado=" + empleado + ", estado=" + estado + ", fechaAlta="
				+ fechaAlta + ", fechaNacimiento=" + fechaNacimiento + ", genero=" + genero + ", noClienteUnico="
				+ noClienteUnico + ", nombre=" + nombre + ", rfc=" + rfc + ", sucursal=" + sucursal + ", tarjetas="
				+ tarjetas + ", telefonoCasa=" + telefonoCasa + ", telefonoCelular=" + telefonoCelular
				+ ", telefonoOficina=" + telefonoOficina + "]";
	}

}

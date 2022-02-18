package es.iespuertodelacruz.ricardo.Supermercado.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import es.iespuertodelacruz.ricardo.Supermercado.entities.Detallepedido;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Pedido;

public class PedidoDTO {
	private int idpedido;
	
	private String direccionEntrega;

	private byte entregado;

	private byte enviado;

	private long fecha;

	private byte pagado;
	
	private List<Detallepedido> detallepedidos;

	public PedidoDTO() {
		super();
	}

	public PedidoDTO(Pedido p) {
		super();
		this.idpedido = p.getIdpedido();
		this.direccionEntrega = p.getDireccionEntrega();
		this.entregado = p.getEntregado();
		this.enviado = p.getEnviado();
		this.fecha = p.getFecha().getTime();
		this.pagado = p.getPagado();
		this.detallepedidos = p.getDetallepedidos();
	}

	public int getIdpedido() {
		return idpedido;
	}

	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}

	public String getDireccionEntrega() {
		return direccionEntrega;
	}

	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}

	public byte getEntregado() {
		return entregado;
	}

	public void setEntregado(byte entregado) {
		this.entregado = entregado;
	}

	public byte getEnviado() {
		return enviado;
	}

	public void setEnviado(byte enviado) {
		this.enviado = enviado;
	}

	public Date getFecha() {
		return new Date(this.fecha);
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha.getTime();
	}

	public byte getPagado() {
		return pagado;
	}

	public void setPagado(byte pagado) {
		this.pagado = pagado;
	}

	public List<Detallepedido> getDetallepedidos() {
		return detallepedidos;
	}

	public void setDetallepedidos(List<Detallepedido> detallepedidos) {
		this.detallepedidos = detallepedidos;
	}

}

package com.mpsuporteti.mpvendas.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.mpsuporteti.mpvendas.domain.Cliente;
import com.mpsuporteti.mpvendas.domain.ItemPedido;
import com.mpsuporteti.mpvendas.domain.Pedido;

public class PedidoDTO implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 3, max = 120, message = "O tamanho deve estar entre 5 e 120 caracteres")
	private String nome;
	
	private Cliente cliente;
	
	private Set<ItemPedido> itens = new HashSet<>();

	public PedidoDTO() {
		
	}
	
	public PedidoDTO(Pedido obj) {
		id = obj.getId();
		itens = obj.getItens();
		setCliente(obj.getCliente());
		nome = obj.getCliente().getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
	
}

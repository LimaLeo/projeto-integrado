package br.com.saojudas.maven.projetointegrado.model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@Column(name = "cpf", length = 15, unique = true, nullable = false)
	private String cpf;

	@Column(name = "login", length = 100, nullable = false)
	private String login;

	@Column(name = "senha", length = 100, nullable = false)
	private String senha;

	@Column(name = "horarioDeAcesso", length = 100, nullable = false)
	private String horarioDeAcesso;

	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUSuario;

	@Column(name = "acessoLivre", nullable = false)
	private boolean acessoLivre;

	@Column(name = "permissaoAlterarTemperatura", nullable = false)
	private boolean permissaoAlterarTemperatura;

	@ManyToOne
	private Empresa empresa_usuario;
	
	@OneToMany(mappedBy="usuario")
	private List<Acesso> acessos;

	public Usuario() {

	}
	
	public Usuario(String nome, String cpf, String login, String senha, String horarioDeAcesso, TipoUsuario tipoUSuario,
			boolean acessoLivre, boolean permissaoAlterarTemperatura) {
		setNome(nome);
		setCpf(cpf);
		setLogin(login);
		setSenha(senha);
		setHorarioDeAcesso(horarioDeAcesso);
		setTipoUsuario(tipoUSuario);
		setAcessoLivre(acessoLivre);
		setPermissaoAlterarTemperatura(permissaoAlterarTemperatura);
	}

	public Usuario(String nome, String cpf, String login, String senha, String horarioDeAcesso, TipoUsuario tipoUSuario,
			boolean acessoLivre, boolean permissaoAlterarTemperatura, Empresa empresa_usuario) {
		setNome(nome);
		setCpf(cpf);
		setLogin(login);
		setSenha(senha);
		setHorarioDeAcesso(horarioDeAcesso);
		setTipoUsuario(tipoUSuario);
		setAcessoLivre(acessoLivre);
		setPermissaoAlterarTemperatura(permissaoAlterarTemperatura);
		setEmpresa(empresa_usuario);
	}
	
	public Usuario(String nome, String cpf, String login, String senha, String horarioDeAcesso, TipoUsuario tipoUSuario,
			boolean acessoLivre, boolean permissaoAlterarTemperatura, Empresa empresa_usuario, boolean nada) {
		setNome(nome);
		setCpf(cpf);
		setLogin(login);
		setSenha(senha);
		this.horarioDeAcesso = horarioDeAcesso;
		setTipoUsuario(tipoUSuario);
		setAcessoLivre(acessoLivre);
		setPermissaoAlterarTemperatura(permissaoAlterarTemperatura);
		setEmpresa(empresa_usuario);
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
		if (nome == null) {
			throw new RuntimeException("O nome nao pode ser nulo!");
		} else if (nome.trim().equals("")) {
			throw new RuntimeException("O nome nao pode ser vazio!");
		} else {
			this.nome = nome;
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {

		if (cpf.equals("")) {
			throw new RuntimeException("O cpf nao pode ser nulo!");
		} else if (cpf.trim().equals("")) {
			throw new RuntimeException("O cpf  nao pode ser vazio!");
		} else {
			this.cpf = cpf;
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		if (login == null) {
			throw new RuntimeException("O login nao pode ser nulo!");
		} else if (login.trim().equals("")) {
			throw new RuntimeException("O login nao pode ser vazio!");
		} else {
			this.login = login;
		}
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		if (senha == null) {
			throw new RuntimeException("O senha nao pode ser nulo!");
		} else if (senha.trim().equals("")) {
			throw new RuntimeException("O senha nao pode ser vazio!");
		} else {
			this.senha = senha;
		}
	}

	public String getHorarioDeAcesso() {
		return horarioDeAcesso;
	}

	public void setHorarioDeAcesso(String horarioDeAcesso) {
		String horarioEntrada = horarioDeAcesso.substring(0,5);		
		String horarioSaida = horarioDeAcesso.substring(8, 13);
		
		if (horarioDeAcesso == null) {
			throw new RuntimeException("O horario de acesso nao pode ser nulo!");
		} else if (horarioDeAcesso.trim().equals("")) {
			throw new RuntimeException("O horario de acesso nao pode ser vazio!");
		} else if ( validarHoras(horarioEntrada) == false || validarHoras(horarioSaida) == false)
		{
			throw new RuntimeException("Horario inválido!");			
		}
		else			
		{
			this.horarioDeAcesso = horarioDeAcesso;
		}
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUSuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUSuario) {
		this.tipoUSuario = tipoUSuario;
	}

	public boolean isAcessoLivre() {
		return acessoLivre;
	}

	public void setAcessoLivre(boolean acessoLivre) {
		this.acessoLivre = acessoLivre;
	}

	public boolean isPermissaoAlterarTemperatura() {
		return permissaoAlterarTemperatura;
	}

	public void setPermissaoAlterarTemperatura(boolean permissaoAlterarTemperatura) {
		this.permissaoAlterarTemperatura = permissaoAlterarTemperatura;
	}

	public Empresa getEmpresa() {
		return empresa_usuario;
	}
	
	public void setEmpresa(Empresa empresa_usuario) {
		this.empresa_usuario = empresa_usuario;
	}
	

	public String toString() {
		return "DADOS USUARIO"+ "\n" + getId() + "\n" + getNome() + "\n" + getCpf() + "\n" + getLogin() + "\n" + getSenha() + "\n"
				+ getHorarioDeAcesso() + "\n" + getTipoUsuario() + "\n" + isAcessoLivre() + "\n"
				+ isPermissaoAlterarTemperatura() + "\n" + getEmpresa().getCnpj();
	}
	
	public boolean validarHoras(String horario)
	{
		int horas=0;
        int minutos=0;
        String horasStr = horario.substring(0,2);
        String minutosStr = horario.substring(3,5);
        try {
            horas = Integer.parseInt(horasStr);
            minutos = Integer.parseInt(minutosStr);
        } catch (Exception e) {
            return false;
        }
            if (horas < 0 || horas > 23)
            {
                return false;
            }
            else
            {
                if (minutos <0 || minutos >59)
                {
                     return false;
                }
            }
            return true;
	}
}

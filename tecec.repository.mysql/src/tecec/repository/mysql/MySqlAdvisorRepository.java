package tecec.repository.mysql;

import java.util.UUID;

import tecec.contract.repository.AdvisorRepository;
import tecec.dto.Advisor;
import tecec.repository.mysql.base.MySqlConnectionConfig;
import tecec.repository.mysql.base.MySqlRepository;

public class MySqlAdvisorRepository extends MySqlRepository implements
		AdvisorRepository {

	public MySqlAdvisorRepository(MySqlConnectionConfig connectionConfig) {
		super(connectionConfig);
	}

	@Override
	public void insertAdvisor(Advisor advisor) {
		try {
			if (advisor.getName() == null || advisor.getName().trim().isEmpty()) {
				throw new IllegalArgumentException(
						"O campo 'nome' do coordenador não pode ser nulo.");
			}

			if (advisor.getPkAdvisor() == null
					|| advisor.getPkAdvisor().trim().isEmpty()) {
				advisor.setPkAdvisor(UUID.randomUUID().toString());
			} else {
				if (advisor.getPkAdvisor().length() != 36) {
					throw new IllegalArgumentException(
							"A chave primária do coordenador deve ser um UUID.");
				}
			}

			if (advisor.getEmail() == null
					|| advisor.getEmail().trim().isEmpty()) {
				throw new IllegalArgumentException(
						"O campo 'e-mail' não pode ser nulo.");
			}

			String command = "INSERT INTO Advisor (PkAdvisor, Name, Email) VALUES ('%1s', '%2s', '%3s')";

			command = String.format(command, advisor.getPkAdvisor(),
					advisor.getName(), advisor.getEmail());

			this.jdbcTemplate.getJdbcOperations().update(command);
		} catch (Exception e) {
			throw new RuntimeException(
					"Ocorreu um erro durante a inserção de um novo coordenador: "
							+ e.getMessage(), e);
		}
	}

}

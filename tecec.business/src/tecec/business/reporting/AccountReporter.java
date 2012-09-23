package tecec.business.reporting;

import java.util.*;

import tecec.contract.io.IReportExporter;
import tecec.contract.reporting.*;
import tecec.dto.record.*;

public class AccountReporter extends BaseReporter<AccountRecord> implements IAccountReporter {

	public AccountReporter(IReportExporter exporter) {
		super(exporter);
	}

	@Override
	public String[] format(AccountRecord source) {
		ArrayList<String> result = new ArrayList<String>();
		
		result.add(source.getUserName());
		result.add(source.getStudentName());
		result.add(source.getProfileName());
		
		return result.toArray(new String[3]);
	}
	
	@Override
	public String[] formatHeader() {
		return new String[] { "Usuário", "Nome", "Perfil" };
	}

}

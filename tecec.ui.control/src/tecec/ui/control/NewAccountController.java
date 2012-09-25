package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.writer.IAccountWriter;
import tecec.ui.contract.control.INewAccountController;

public class NewAccountController extends BaseController implements INewAccountController {

	String id;
	String userName;
	
	IAccountWriter accountWriter;	
	
	public NewAccountController(IAccountWriter accountWriter) {
		this.accountWriter = accountWriter;
	}

	@Override
	public void refresh() {
		setID("");
	}

	@Override
	public void setID(String id) {
		this.id = id;
		
		super.notifyOfPropertyChange("id");
	}

	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
		
		super.notifyOfPropertyChange("userName");
	}

	@Override
	public String getUserName() {
		return this.userName;
	}

	@Override
	public RuleViolation getInsertViolation() {		
		if (this.id == null || this.id.isEmpty()) {
			return new RuleViolation("O ID do usuário deve ser preenchido.");
		}
		if (this.userName == null || this.userName.isEmpty()) {
			return new RuleViolation("O nome do usuário deve ser preenchido.");
		}
		
		return null;
	}

	@Override
	public void insert()
			throws RuleViolationException {
		RuleViolation violation = getInsertViolation();
		
		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		
		this.accountWriter.insertAccount(this.id, this.id, userName, null);
		
		refresh();
	}
}

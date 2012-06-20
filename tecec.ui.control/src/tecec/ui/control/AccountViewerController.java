package tecec.ui.control;

import java.util.ArrayList;
import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IAccountReader;
import tecec.contract.reader.IProfileReader;
import tecec.contract.reader.IStudentReader;
import tecec.contract.writer.IAccountWriter;
import tecec.dto.Account;
import tecec.dto.Profile;
import tecec.dto.Student;
import tecec.ui.contract.control.IAccountViewerController;
import tecec.ui.contract.record.AccountRecord;
import tecec.ui.contract.view.INewAccountUI;
import tecec.ui.contract.view.IUpdateAccountUI;

public class AccountViewerController extends BaseController implements IAccountViewerController {

	String filter;
	AccountRecord selectedAccount;
	
	IAccountReader accountReader;
	IAccountWriter accountWriter;
	IStudentReader studentReader;
	IProfileReader profileReader;
	
	INewAccountUI newAccountUI;
	IUpdateAccountUI updateAccountUI;
	
	public AccountViewerController(IAccountReader accountReader,
			IAccountWriter accountWriter, IStudentReader studentReader,
			IProfileReader profileReader, INewAccountUI newAccountUI,
			IUpdateAccountUI updateAccountUI) {
		this.accountReader = accountReader;
		this.accountWriter = accountWriter;
		this.studentReader = studentReader;
		this.profileReader = profileReader;
		this.newAccountUI = newAccountUI;
		this.updateAccountUI = updateAccountUI;
	}

	@Override
	public void setFilter(String filter) {
		this.filter = filter;
		
		super.notifyOfPropertyChange("accounts", null, getAccounts());
	}

	@Override
	public String getFilter() {
		return this.filter;
	}

	@Override
	public List<AccountRecord> getAccounts() {
		List<Account> accounts = this.accountReader.getAccounts(this.filter);
		
		List<AccountRecord> result = new ArrayList<AccountRecord>();
		
		for(Account account : accounts){
			AccountRecord record = new AccountRecord();
			
			record.setId(account.getId());
			record.setUserName(account.getUserName());
			
			if (account.getFKStudent() != null) {
				Student student = this.studentReader.getStudentByPk(account.getFKStudent());
				
				record.setfKStudent(student.getPKStudent());
				record.setStudentName(student.getName());				
			}			
			
			if (account.getfKProfile() != null) {
				Profile profile = this.profileReader.getProfileByPK(account.getfKProfile());
				
				record.setProfileName(profile.getName());
			}
			
			record.setfKStudent(account.getFKStudent());
			
			result.add(record);
		}
		
		return result;
	}

	@Override
	public AccountRecord getSelectedAccount() {
		return this.selectedAccount;
	}

	@Override
	public void setSelectedAccount(AccountRecord account) {
		this.selectedAccount = account;
		
		super.notifyOfPropertyChange("selectedAccount", null, selectedAccount);
		super.notifyOfPropertyChange("canUpdate", null, getCanUpdate());
		super.notifyOfPropertyChange("canDelete", null, getCanDelete());
	}

	@Override
	public boolean getCanUpdate() {
		return this.selectedAccount != null;
	}

	@Override
	public boolean getCanDelete() {
		return this.selectedAccount != null;
	}

	@Override
	public void showUpdateAccountUI() {
		this.updateAccountUI.setAccountID(this.getSelectedAccount().getId());
		this.updateAccountUI.setVisible(true);

		super.notifyOfPropertyChange("accounts", null, getAccounts());
	}

	@Override
	public void showNewAccountUI() {
		this.newAccountUI.setVisible(true);

		super.notifyOfPropertyChange("accounts", null, getAccounts());
	}

	@Override
	public void delete() throws RuleViolationException {
		this.accountWriter.deleteAccount(this.getSelectedAccount().getId());

		super.notifyOfPropertyChange("accounts", null, getAccounts());
	}

	@Override
	public RuleViolation getDeletionViolation() {
		return this.accountWriter.getDeletionViolation(this.getSelectedAccount().getId());
	}

	@Override
	public void refresh() {
		super.notifyOfPropertyChange("accounts", null, getAccounts());
	}
	
}

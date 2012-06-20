package tecec.ui.control;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class BaseController  {
	private final PropertyChangeSupport propertyChanger;

	public BaseController() {
		propertyChanger = new PropertyChangeSupport(this);
	}
	
	protected void notifyOfPropertyChange(String propertyName, Object oldValue, Object newValue)
	{
		propertyChanger.firePropertyChange(propertyName, oldValue, newValue);
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		propertyChanger.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		propertyChanger.removePropertyChangeListener(l);
	}
}

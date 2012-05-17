package tecec.contract.reader;

import java.util.List;

import tecec.dto.Stage;

public interface IStageReader {
	List <Stage> getStage (String nameFilter);
	Stage getStageByPK (String pKStage);
}

package tecec.business.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.repository.ICourseRepository;
import tecec.dto.Course;




public class CourseWriter implements tecec.contract.writer.ICourseWriter {


	private tecec.contract.repository.ICourseRepository courseRepository;

	public CourseWriter(ICourseRepository courseRepository) {
		if (courseRepository == null) {
			throw new IllegalArgumentException("courseRepository");
		}

		this.courseRepository = courseRepository;
	}
	
	@Override
	public void deleteCourse(String pKCourse) {
		this.courseRepository.deleteCourse(pKCourse);
	}

	@Override
	public RuleViolation getCreationViolation(String name, String turn,
			String year) {
		
		if (name == null || name.trim().isEmpty()) {
			return new RuleViolation("O nome do curso deve ser preenchido.");
		} else {
			if (name.length() > 128) {
				return new RuleViolation(
						"O nome do curso deve ser menor que 128 caracteres.");
			}
		}
		
		if (year == null || year.trim().isEmpty()) {
			return new RuleViolation ("O ano do curso deve ser preenchido.");
		} else {
			if (year.length() != 4) {
				return new RuleViolation("O ano do curso deve ter 4 caracteres.");
			}
		}
		
		if (turn == null || turn.trim().isEmpty()) {
			return new RuleViolation ("O turno do curso deve ser preenchido.");
		} else {
			if (turn.length() > 128) {
				return new RuleViolation ("O turno do curso deve ser menor que 128 caracteres.");
			}
		}
		
		Course course = courseRepository.getCourseByNameAndTurnAndYear(name, turn, year);
		
		if (course != null)
			return new RuleViolation ("Já existe outro curso cadastrado com estes dados.");
		
		return null;
		
	}

	@Override
	public RuleViolation getUpdateViolation(Course newCourse) {
		
		Course course = this.courseRepository.getCourseByPK(newCourse.getPKCourse());
		
		if (course == null)
			return new RuleViolation ("O curso selecionado não existe no banco de dados.");
		
		if (newCourse.getName()== null || newCourse.getName().trim().isEmpty()) {
			return new RuleViolation ("O nome do curso deve ser preenchido.");
		}
		
		if (newCourse.getYear() == null || newCourse.getYear().trim().isEmpty() 
				|| newCourse.getYear().length() != 4 ) {
			return new RuleViolation ("O ano do curso deve ser válido");
		}
		
		if (newCourse.getTurn()== null || newCourse.getTurn().trim().isEmpty()) {
			return new RuleViolation ("O turno do curso deve ser preenchido.");
		}
		
		course = this.courseRepository.getCourseByNameAndTurnAndYear(newCourse.getName(),
				newCourse.getTurn(), newCourse.getYear());
		
		if (course != null) {
			if (!course.getPKCourse().equals(newCourse.getPKCourse())) {
				return new RuleViolation ("Já existe outro curso cadastrado com estes dados.");
			}
		}
		return null;
	}

	@Override
	public void createCourse(String name, String turn, String year)
			throws RuleViolationException {
		
		RuleViolation violation = getCreationViolation(name, turn, year);
		
		if (violation != null)
			throw new RuleViolationException (violation);
		
		Course course = new Course ();
		course.setName(name);
		course.setTurn(turn);
		course.setYear(year);
		
		this.courseRepository.insertCourse(course);
		
	}

	@Override
	public void updateCourse(String pKCourse, String newName, String newTurn,
			String newYear) throws RuleViolationException {
		
		RuleViolation violation = getCreationViolation (newName, newTurn, newYear);
		
		if (violation != null)
			throw new RuleViolationException (violation);
		
		Course course = new Course ();
		course.setName(newName);
		course.setTurn(newTurn);
		course.setYear(newYear);
		course.setPKCourse(pKCourse);
		
		this.courseRepository.updateCourse(course);
	}

}

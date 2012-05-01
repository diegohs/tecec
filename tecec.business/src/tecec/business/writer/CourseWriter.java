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
	public void createCourse(String name) throws RuleViolationException {
		RuleViolation violation = getCreationViolation(name);

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		Course course = new Course();

		course.setName(name);

		this.courseRepository.insertCourse(course);
	}

	@Override
	public void updateCourse(String pKCourse, String newName) throws RuleViolationException {
		RuleViolation violation = getUpdateViolation(pKCourse, newName);
		
		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		
		Course course = new Course();
		
		course.setPKCourse(pKCourse);
		course.setName(newName);
		
		this.courseRepository.updateCourse(course);
	}

	@Override
	public RuleViolation getCreationViolation(String name) {
		if (name == null || name.trim().isEmpty()) {
			return new RuleViolation("O nome do curso deve ser preenchido.");
		} else {
			if (name.length() > 128) {
				return new RuleViolation(
						"O nome do curso deve ser menor que 128 caracteres.");
			}
		}

		Course course = courseRepository.getCourseByName(name);

		if (course != null) {
			return new RuleViolation(
					"Já existe outro curso cadastrado com o mesmo nome.");
		}

		return null;
	}

	@Override
	public RuleViolation getUpdateViolation(String pKCourse, String newName) {
		Course course = this.courseRepository.getCourseByPK(pKCourse);

		if (course == null) {
			return new RuleViolation(
					"O curso selecionado não existe no banco de dados.");
		}
		
		if (newName == null || newName.trim().isEmpty()) {
			return new RuleViolation("O nome do curso deve ser preenchido.");
		}

		course = this.courseRepository.getCourseByName(newName);

		if (course != null) {
			if (!course.getPKCourse().equals(pKCourse)) {
				return new RuleViolation(
						"Já existe outro curso cadastrado com este nome.");
			}
		}

		return null;
	}

}

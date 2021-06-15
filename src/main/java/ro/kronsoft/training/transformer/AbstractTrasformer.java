package ro.kronsoft.training.transformer;

import ro.kronsoft.training.dto.BaseDto;
import ro.kronsoft.training.entitis.BaseEntity;

public abstract class AbstractTrasformer<T extends BaseEntity, X extends BaseDto> {

public abstract X toDto(T entity);

public abstract T toEntity(X dto);

protected abstract T findOrCreateNew(Long id);
	
	
	
}

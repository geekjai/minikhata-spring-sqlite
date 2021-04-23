package ig.central.library;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Transient;

import ig.central.library.annotation.UpdateCacheEntityColumn;
import ig.central.library.constant.WhoColumnDataConstant;

public class FrameworkEntity {

	@EntityState
	private String entityState;

	@Transient
	private Boolean isSeedData;

	public final String getEntityState() {
		return entityState;
	}

	public final void setEntityState(String entityState) {
		this.entityState = entityState;
	}

	public Boolean getIsSeedData() {
		return isSeedData;
	}

	public void setIsSeedData(Boolean isSeedData) {
		this.isSeedData = isSeedData;
	}

	/**
	 * Filter out proxy class and return only declared fields of hibernate entity
	 * which has inherrited FrameworkEntity
	 * 
	 * @param entity
	 * @return
	 */
	private static Field[] frameworkEntityDeclaredFields(FrameworkEntity entity) {

		if (entity != null) {
			if (entity.getClass().getCanonicalName().contains("EnhancerByCGLIB")) {
				return entity.getClass().getSuperclass().getDeclaredFields();
			} else {
				return entity.getClass().getDeclaredFields();
			}
		}

		return null;
	}

	public static final void setFrameworkEntityData(FrameworkEntity entity, Map<String, Object> row)
			throws InstantiationException, IllegalAccessException {

		if (entity == null || row == null) {
			return;
		}

		for (Field field : frameworkEntityDeclaredFields(entity)) {

			Column column = field.getAnnotation(Column.class);
			TransientColumn transientColumn = field.getAnnotation(TransientColumn.class);

			// Logic to handle DB column
			if (column != null || transientColumn != null) {

				String columnName = null;
				if (column != null) {
					columnName = column.name();
				} else if (transientColumn != null) {
					columnName = transientColumn.name();
				}

				Class<?> classType = field.getType();
				field.setAccessible(true);

				// Handle String data type
				if (classType.equals(String.class)) {
					Object objData = row.get(columnName);
					if (objData instanceof String) {
						String strData = (String) row.get(columnName);
						field.set(entity, strData);
					}
				}
				// Handle Long and BigInteger data type
				else if (classType.equals(Long.class)) {
					Object objData = row.get(columnName);
					if (objData instanceof Integer) {
						field.set(entity, Long.parseLong(Integer.toString((int) objData)));
					} else if (objData instanceof BigInteger) {
						field.set(entity, ((BigInteger) objData).longValue());
					} else {
						field.set(entity, (Long) objData);
					}
				}
				// Handle Date data type
				else if (classType.equals(Date.class)) {
					Object objData = row.get(columnName);
					if (objData instanceof Date) {
						field.set(entity, (Date) objData);
					}
				}
				// Handle Double
				else if (classType.equals(Double.class)) {
					Object objData = row.get(columnName);
					if (objData instanceof Float) {
						field.set(entity, Double.parseDouble(Float.toString((float) objData)));
					} else if (objData instanceof BigDecimal) {
						field.set(entity, ((BigDecimal) objData).doubleValue());
					} else if (objData instanceof Integer) {
						field.set(entity, ((Integer) objData).doubleValue());
					} else {
						field.set(entity, (Double) objData);
					}
				}
			}
		}

		entity.setEntityState(EntityStateConstant.EXISTING_ENTITY);
	}

	public static final Map<String, Field> getFrameworkEntityFieldMap(FrameworkEntity entity)
			throws IllegalArgumentException, IllegalAccessException {

		if (entity == null)
			return null;

		Map<String, Field> fieldMap = new HashMap<String, Field>();
		/**
		 * Filter out proxy class and get entity declared fields
		 */
		for (Field field : frameworkEntityDeclaredFields(entity)) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				fieldMap.put(column.name(), field);
			}
		}
		return fieldMap;
	}

	public static void updateCachedEntity(FrameworkEntity cachedEntity, FrameworkEntity entity)
			throws IllegalArgumentException, IllegalAccessException {

		if (entity == null || entity == null)
			return;

		Map<String, Field> cachedEntityFieldMap = FrameworkEntity.getFrameworkEntityFieldMap(cachedEntity);

		for (Field field : frameworkEntityDeclaredFields(entity)) {
			Column column = field.getAnnotation(Column.class);
			UpdateCacheEntityColumn cacheUpdate = field.getAnnotation(UpdateCacheEntityColumn.class);
			if (column != null) {
				if (column.updatable()) {
					// check central library supported annotation
					if (cacheUpdate != null) {
						if (!cacheUpdate.value()) {
							continue;
						}
					}
					Field cahcedField = cachedEntityFieldMap.get(column.name());
					Class<?> classType = field.getType();
					field.setAccessible(true);
					if (classType.equals(String.class)) {
						Object objData = field.get(entity);
						if (objData instanceof String) {
							cahcedField.setAccessible(true);
							cahcedField.set(cachedEntity, (String) objData);
						}
					} else if (classType.equals(Long.class)) {
						Object objData = field.get(entity);
						if (objData instanceof Integer) {
							cahcedField.setAccessible(true);
							cahcedField.set(cachedEntity, (Integer) objData);
						} else if (objData instanceof BigInteger) {
							cahcedField.setAccessible(true);
							cahcedField.set(cachedEntity, (BigInteger) objData);
						} else {
							cahcedField.setAccessible(true);
							cahcedField.set(cachedEntity, (Long) objData);
						}
					} else if (classType.equals(Date.class)) {
						Object objData = field.get(entity);
						if (objData instanceof Date) {
							cahcedField.setAccessible(true);
							cahcedField.set(cachedEntity, (Date) objData);
						}
					} else if (classType.equals(java.sql.Date.class)) {
						Object objData = field.get(entity);
						if (objData instanceof java.sql.Date) {
							cahcedField.setAccessible(true);
							cahcedField.set(cachedEntity, (java.sql.Date) objData);
						}
					}
				}
			}
		}
	}

	public static void createWhoColumnData(FrameworkEntity entity, boolean isSeedData)
			throws IllegalArgumentException, IllegalAccessException {

		if (entity == null) {
			return;
		}
		if (entity.getIsSeedData() == null) {
			entity.setIsSeedData(false);
		}
		// Using [javax.persistence.Column] JPA and Reflection
		for (Field field : entity.getClass().getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				if ("created_by".equals(column.name())) {
					field.setAccessible(true);
					if (isSeedData) {
						field.set(entity, WhoColumnDataConstant.SEED_DATA_USER_NAME);
					} else {
						field.set(entity, "Test User");
					}
				} else if ("last_updated_by".equals(column.name())) {
					field.setAccessible(true);
					if (isSeedData) {
						field.set(entity, WhoColumnDataConstant.SEED_DATA_USER_NAME);
					} else {
						field.set(entity, "Test User");
					}
				} else if ("creation_date".equals(column.name()) || "last_update_date".equals(column.name())) {
					Class<?> classType = field.getType();
					if (classType.equals(java.sql.Date.class)) {
						field.setAccessible(true);
						java.util.Date curr = Calendar.getInstance().getTime();
						java.sql.Date sqlDate = new java.sql.Date(curr.getTime());
						field.set(entity, sqlDate);
					} else if (classType.equals(java.util.Date.class)) {
						field.setAccessible(true);
						field.set(entity, Calendar.getInstance().getTime());
					}
				}
			}
		}
	}

}

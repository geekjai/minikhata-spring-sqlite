package ig.central.library.sqlite;

import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
import org.springframework.data.mapping.MappingException;

public class SqliteIdentityColumnSupport extends IdentityColumnSupportImpl {

	@Override
	public boolean supportsIdentityColumns() {
		return true;
	}

	@Override
	public String getIdentitySelectString(String table, String column, int type) throws MappingException {
		return "select last_insert_rowid()";
	}

	@Override
	public String getIdentityColumnString(int type) throws MappingException {
		return "integer";
	}

}
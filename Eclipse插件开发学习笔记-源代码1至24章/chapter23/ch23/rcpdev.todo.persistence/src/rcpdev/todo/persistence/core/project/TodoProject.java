package rcpdev.todo.persistence.core.project;

import oracle.toplink.descriptors.ClassDescriptor;
import oracle.toplink.descriptors.RelationalDescriptor;
import oracle.toplink.mappings.DirectToFieldMapping;
import oracle.toplink.mappings.OneToManyMapping;
import oracle.toplink.mappings.OneToOneMapping;
import oracle.toplink.sequencing.TableSequence;
import oracle.toplink.sessions.DatabaseLogin;
import rcpdev.todo.persistence.Activator;
import rcpdev.todo.persistence.preferences.PreferenceConstants;

/**
 * This class was generated by the TopLink project class generator. It stores
 * the meta-data (descriptors) that define the TopLink mappings. ## Oracle
 * TopLink - 10g Release 3 (10.1.3.0.0) (Build 060118) ##
 * 
 * @see oracle.toplink.tools.workbench.ProjectClassGenerator
 */

public class TodoProject extends oracle.toplink.sessions.Project {

	public TodoProject() {
		setName("todo");
		applyLogin();

		addDescriptor(buildTodoItemDescriptor());
		addDescriptor(buildSeriesInfoDescriptor());
		addDescriptor(buildTodoSeriesDescriptor());
		addDescriptor(buildDayInfoDescriptor());
		addDescriptor(buildMonthInfoDescriptor());
		addDescriptor(buildWeekInfoDescriptor());
		addDescriptor(buildYearInfoDescriptor());
	}

	public void applyLogin() {
		DatabaseLogin login = new DatabaseLogin();
		login
				.usePlatform(new oracle.toplink.platform.database.MySQL4Platform());
		login.setDriverClassName("com.mysql.jdbc.Driver");
		login.setConnectionString(Activator.getDefault().getPreferenceStore()
				.getString(PreferenceConstants.P_MYSQL_CONSTR));
		login.setUserName(Activator.getDefault().getPreferenceStore()
				.getString(PreferenceConstants.P_MYSQL_USERNAME));
		login.setPassword(Activator.getDefault().getPreferenceStore()
				.getString(PreferenceConstants.P_MYSQL_PASSWORD));

		// Configuration Properties.

		// Sequencing.
		login.setDefaultSequence(new TableSequence("", 50, "sequence", "name",
				"count"));

		setDatasourceLogin(login);
	}

	public ClassDescriptor buildDayInfoDescriptor() {
		RelationalDescriptor descriptor = new RelationalDescriptor();
		descriptor
				.setJavaClass(rcpdev.todo.core.model.series.info.DayInfo.class);
		descriptor.addTableName("todo_series_info");

		// Inheritance Properties.
		descriptor.getInheritancePolicy().setParentClass(
				rcpdev.todo.core.model.series.SeriesInfo.class);

		// Descriptor Properties.
		descriptor.setAlias("DayInfo");

		// Query Manager.
		descriptor.getQueryManager().checkCacheForDoesExist();

		// Event Manager.

		// Mappings.
		DirectToFieldMapping infoTypeMapping = new DirectToFieldMapping();
		infoTypeMapping.setAttributeName("infoType");
		infoTypeMapping.setFieldName("todo_series_info.day_type");
		descriptor.addMapping(infoTypeMapping);

		return descriptor;
	}

	public ClassDescriptor buildMonthInfoDescriptor() {
		RelationalDescriptor descriptor = new RelationalDescriptor();
		descriptor
				.setJavaClass(rcpdev.todo.core.model.series.info.MonthInfo.class);
		descriptor.addTableName("todo_series_info");

		// Inheritance Properties.
		descriptor.getInheritancePolicy().setParentClass(
				rcpdev.todo.core.model.series.SeriesInfo.class);

		// Descriptor Properties.
		descriptor.setAlias("MonthInfo");

		// Query Manager.
		descriptor.getQueryManager().checkCacheForDoesExist();

		// Event Manager.

		// Mappings.
		DirectToFieldMapping dayOfMonthMapping = new DirectToFieldMapping();
		dayOfMonthMapping.setAttributeName("dayOfMonth");
		dayOfMonthMapping.setFieldName("todo_series_info.month_day");
		descriptor.addMapping(dayOfMonthMapping);

		return descriptor;
	}

	public ClassDescriptor buildSeriesInfoDescriptor() {
		RelationalDescriptor descriptor = new RelationalDescriptor();
		descriptor.setJavaClass(rcpdev.todo.core.model.series.SeriesInfo.class);
		descriptor.addTableName("todo_series_info");
		descriptor.addPrimaryKeyFieldName("todo_series_info.id");

		// Inheritance Properties.
		descriptor.getInheritancePolicy().setClassIndicatorFieldName(
				"todo_series_info.type");
		descriptor.getInheritancePolicy().addClassIndicator(
				rcpdev.todo.core.model.series.info.WeekInfo.class, "WEEK");
		descriptor.getInheritancePolicy().addClassIndicator(
				rcpdev.todo.core.model.series.info.DayInfo.class, "DAY");
		descriptor.getInheritancePolicy().addClassIndicator(
				rcpdev.todo.core.model.series.info.YearInfo.class, "YEAR");
		descriptor.getInheritancePolicy().addClassIndicator(
				rcpdev.todo.core.model.series.info.MonthInfo.class, "MONTH");

		// Descriptor Properties.
		descriptor.useSoftCacheWeakIdentityMap();
		descriptor.setIdentityMapSize(100);
		descriptor.useRemoteSoftCacheWeakIdentityMap();
		descriptor.setRemoteIdentityMapSize(100);
		descriptor.setSequenceNumberFieldName("todo_series_info.id");
		descriptor.setSequenceNumberName("todo_series_info");
		descriptor.setAlias("SeriesInfo");

		// Query Manager.
		descriptor.getQueryManager().checkCacheForDoesExist();

		// Event Manager.

		// Mappings.
		DirectToFieldMapping baseDateMapping = new DirectToFieldMapping();
		baseDateMapping.setAttributeName("baseDate");
		baseDateMapping.setFieldName("todo_series_info.baseDate");
		descriptor.addMapping(baseDateMapping);

		DirectToFieldMapping idMapping = new DirectToFieldMapping();
		idMapping.setAttributeName("id");
		idMapping.setFieldName("todo_series_info.id");
		descriptor.addMapping(idMapping);

		DirectToFieldMapping intervalMapping = new DirectToFieldMapping();
		intervalMapping.setAttributeName("interval");
		intervalMapping.setFieldName("todo_series_info.span");
		descriptor.addMapping(intervalMapping);

		OneToOneMapping nextMapping = new OneToOneMapping();
		nextMapping.setAttributeName("next");
		nextMapping
				.setReferenceClass(rcpdev.todo.core.model.series.SeriesInfo.class);
		nextMapping.dontUseIndirection();
		nextMapping.privateOwnedRelationship();
		nextMapping.addForeignKeyFieldName("todo_series_info.next",
				"todo_series_info.id");
		descriptor.addMapping(nextMapping);

		return descriptor;
	}

	public ClassDescriptor buildTodoItemDescriptor() {
		RelationalDescriptor descriptor = new RelationalDescriptor();
		descriptor.setJavaClass(rcpdev.todo.core.model.TodoItem.class);
		descriptor.addTableName("todo_item");
		descriptor.addPrimaryKeyFieldName("todo_item.id");

		// Inheritance Properties.
		descriptor.getInheritancePolicy().setClassIndicatorFieldName(
				"todo_item.type");
		descriptor.getInheritancePolicy().addClassIndicator(
				rcpdev.todo.core.model.series.TodoSeries.class, "SERIES");
		descriptor.getInheritancePolicy().addClassIndicator(
				rcpdev.todo.core.model.TodoItem.class, "SINGLE");

		// Descriptor Properties.
		descriptor.useSoftCacheWeakIdentityMap();
		descriptor.setIdentityMapSize(100);
		descriptor.useRemoteSoftCacheWeakIdentityMap();
		descriptor.setRemoteIdentityMapSize(100);
		descriptor.setSequenceNumberFieldName("todo_item.id");
		descriptor.setSequenceNumberName("todo_item");
		descriptor.setAlias("TodoItem");

		// Query Manager.
		descriptor.getQueryManager().checkCacheForDoesExist();

		// Event Manager.

		// Mappings.
		DirectToFieldMapping contentMapping = new DirectToFieldMapping();
		contentMapping.setAttributeName("content");
		contentMapping.setFieldName("todo_item.content");
		descriptor.addMapping(contentMapping);

		DirectToFieldMapping dateMapping = new DirectToFieldMapping();
		dateMapping.setAttributeName("date");
		dateMapping.setFieldName("todo_item.start_date");
		descriptor.addMapping(dateMapping);

		DirectToFieldMapping idMapping = new DirectToFieldMapping();
		idMapping.setAttributeName("id");
		idMapping.setFieldName("todo_item.id");
		descriptor.addMapping(idMapping);

		DirectToFieldMapping startTimeMapping = new DirectToFieldMapping();
		startTimeMapping.setAttributeName("startTime");
		startTimeMapping.setFieldName("todo_item.start_time");
		descriptor.addMapping(startTimeMapping);

		DirectToFieldMapping stopTimeMapping = new DirectToFieldMapping();
		stopTimeMapping.setAttributeName("stopTime");
		stopTimeMapping.setFieldName("todo_item.stop_time");
		descriptor.addMapping(stopTimeMapping);

		DirectToFieldMapping subjectMapping = new DirectToFieldMapping();
		subjectMapping.setAttributeName("subject");
		subjectMapping.setFieldName("todo_item.subject");
		descriptor.addMapping(subjectMapping);

		OneToOneMapping seriesMapping = new OneToOneMapping();
		seriesMapping.setAttributeName("series");
		seriesMapping
				.setReferenceClass(rcpdev.todo.core.model.series.TodoSeries.class);
		seriesMapping.dontUseIndirection();
		seriesMapping
				.addForeignKeyFieldName("todo_item.series", "todo_item.id");
		descriptor.addMapping(seriesMapping);

		return descriptor;
	}

	public ClassDescriptor buildTodoSeriesDescriptor() {
		RelationalDescriptor descriptor = new RelationalDescriptor();
		descriptor.setJavaClass(rcpdev.todo.core.model.series.TodoSeries.class);
		descriptor.addTableName("todo_item");
		descriptor.addTableName("todo_series");

		// Inheritance Properties.
		descriptor.getInheritancePolicy().setParentClass(
				rcpdev.todo.core.model.TodoItem.class);

		// Descriptor Properties.
		descriptor.setAlias("TodoSeries");

		// Query Manager.
		descriptor.getQueryManager().checkCacheForDoesExist();

		// Event Manager.

		// Mappings.
		DirectToFieldMapping seriesTypeMapping = new DirectToFieldMapping();
		seriesTypeMapping.setAttributeName("seriesType");
		seriesTypeMapping.setFieldName("todo_series.series_type");
		descriptor.addMapping(seriesTypeMapping);

		DirectToFieldMapping stopDateMapping = new DirectToFieldMapping();
		stopDateMapping.setAttributeName("stopDate");
		stopDateMapping.setFieldName("todo_series.stop_date");
		descriptor.addMapping(stopDateMapping);

		OneToOneMapping infoMapping = new OneToOneMapping();
		infoMapping.setAttributeName("info");
		infoMapping
				.setReferenceClass(rcpdev.todo.core.model.series.SeriesInfo.class);
		infoMapping.dontUseIndirection();
		infoMapping.privateOwnedRelationship();
		infoMapping.addForeignKeyFieldName("todo_series.series_info",
				"todo_series_info.id");
		descriptor.addMapping(infoMapping);

		OneToManyMapping itemsMapping = new OneToManyMapping();
		itemsMapping.setAttributeName("items");
		itemsMapping.setReferenceClass(rcpdev.todo.core.model.TodoItem.class);
		itemsMapping.privateOwnedRelationship();
		itemsMapping.useTransparentCollection();
		itemsMapping
				.useCollectionClass(oracle.toplink.indirection.IndirectList.class);
		itemsMapping.addTargetForeignKeyFieldName("todo_item.series",
				"todo_item.id");
		descriptor.addMapping(itemsMapping);

		return descriptor;
	}

	public ClassDescriptor buildWeekInfoDescriptor() {
		RelationalDescriptor descriptor = new RelationalDescriptor();
		descriptor
				.setJavaClass(rcpdev.todo.core.model.series.info.WeekInfo.class);
		descriptor.addTableName("todo_series_info");

		// Inheritance Properties.
		descriptor.getInheritancePolicy().setParentClass(
				rcpdev.todo.core.model.series.SeriesInfo.class);

		// Descriptor Properties.
		descriptor.setAlias("WeekInfo");
		descriptor
				.setAmendmentClass(rcpdev.todo.persistence.core.project.AfterLoad.class);
		descriptor.setAmendmentMethodName("afterWeekInfoLoad");

		// Query Manager.
		descriptor.getQueryManager().checkCacheForDoesExist();

		// Event Manager.

		descriptor.applyAmendmentMethod();
		return descriptor;
	}

	public ClassDescriptor buildYearInfoDescriptor() {
		RelationalDescriptor descriptor = new RelationalDescriptor();
		descriptor
				.setJavaClass(rcpdev.todo.core.model.series.info.YearInfo.class);
		descriptor.addTableName("todo_series_info");

		// Inheritance Properties.
		descriptor.getInheritancePolicy().setParentClass(
				rcpdev.todo.core.model.series.SeriesInfo.class);

		// Descriptor Properties.
		descriptor.setAlias("YearInfo");

		// Query Manager.
		descriptor.getQueryManager().checkCacheForDoesExist();

		// Event Manager.

		// Mappings.
		DirectToFieldMapping monthOfYearMapping = new DirectToFieldMapping();
		monthOfYearMapping.setAttributeName("monthOfYear");
		monthOfYearMapping.setFieldName("todo_series_info.y_month");
		descriptor.addMapping(monthOfYearMapping);

		return descriptor;
	}

}

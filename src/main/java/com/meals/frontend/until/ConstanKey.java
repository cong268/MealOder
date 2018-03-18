package com.meals.frontend.until;

public class ConstanKey {
	public static final String USER_ID = "userId";
	public static final String USER_NAME = "userName";
	public static final String STAFF_ID = "staffId";
	public static final String USER_ROLE = "userRole";
	
	public interface ROLE {
		public static final String ROLE_ADMIN = "Admin";
		public static final String ROLE_MANAGER = "Manager";
		public static final String ROLE_EMPLOYEE = "Employee";
		public static final String ROLE_CHEF = "Chef";
	}
	
	public interface FORMAT_DATE {
		public static final String DATE_DATA_FORMAT = "dd/MM/yyyy HH:mm:ss";
		public static final String DATE_SLASH_FORMAT = "dd/MM/yyyy";
		public static final String DATE_TIME_FORMAT = "ddMMyyyy";
	}
	
	public interface ALL_CODE {
		public static final String MEAL_TYPE = "MealType";
		public static final String MEAL_TIME = "MealTime";
	}
}

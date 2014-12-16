package com.pentago.controller;

public class Status {
	
	//static -----------------------------------
		public static final int NONE		 = 0;
		public static final int BLACK_WINS	 = 1;
		public static final int RED_WINS	 = 2;
		public static final int DRAW		 = 3;
		public static final int ILLEGAL_MOVE = 4;
		public static final int ERROR		 = 99;

		//----------------------------------------

	    /**
	     * This constructor create an object of the class Status
	     * @param value Sets the value of the object
	     */
		public Status(int value){
			this.value = value;
		}

	    /**
	     * Getter-method to get out the value of the status
	     * @return value
	     */
		public int value() {
			return value;
		}

	    /**
	     * Setter-method to change the status of the object
	     * @param value
	     */
		public void value(int value){
			this.value = value;
		}

		private int value = NONE;
}

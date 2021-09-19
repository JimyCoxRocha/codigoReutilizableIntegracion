package org.mdw.stc.entity.exception;

public class TSException extends Exception {

	private static final long serialVersionUID = 7718828512143293558L;

	private final CodeEnum code;

	public TSException(CodeEnum code) {
		super();
		if (code == null)
			this.code = CodeEnum.BAD_REQUEST;
		else if (code.getCode() == 0)
			this.code = CodeEnum.BAD_REQUEST;
		else
			this.code = code;
	}

	public TSException(String message, Throwable cause, CodeEnum code) {
		super(message, cause);
		if (code == null)
			this.code = CodeEnum.BAD_REQUEST;
		else if (code.getCode() == 0)
			this.code = CodeEnum.BAD_REQUEST;
		else
			this.code = code;
	}

	public TSException(String message, CodeEnum code) {
		super(message);
		if (code == null)
			this.code = CodeEnum.BAD_REQUEST;
		else if (code.getCode() == 0)
			this.code = CodeEnum.BAD_REQUEST;
		else
			this.code = code;
	}

	public TSException(Throwable cause, CodeEnum code) {
		super(cause);
		if (code == null)
			this.code = CodeEnum.BAD_REQUEST;
		else if (code.getCode() == 0)
			this.code = CodeEnum.BAD_REQUEST;
		else
			this.code = code;
	}

	public CodeEnum getCode() {
		return this.code;
	}

}

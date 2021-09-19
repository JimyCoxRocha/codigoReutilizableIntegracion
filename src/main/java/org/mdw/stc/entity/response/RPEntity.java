package org.mdw.stc.entity.response;

public class RPEntity {

	public Boolean error = false;
	public Integer codeError = 200;
	public String message = "OK";
	public TSEntity ts;

	public static RPEntity done(TSEntity et) {
		RPEntity r = new RPEntity();
		r.codeError = 200;
		r.message = "Ok";
		r.ts = et.done(et);

		return r;
	}

	public static RPEntity fail(String message, Integer codeError) {
		RPEntity et = new RPEntity();
		et.codeError = codeError == null ? 404 : codeError;
		et.message = message;
		et.error = true;
		et.ts = new TSEntity();
		et.ts.exiteError = true;
		et.ts.Mensaje = message;

		return et;
	}

	public static RPEntity fail(String message) {
		RPEntity et = new RPEntity();
		et.codeError = 404;
		et.message = message;
		et.error = true;
		et.ts = new TSEntity();
		et.ts.exiteError = true;
		et.ts.Mensaje = message;

		return et;
	}
}

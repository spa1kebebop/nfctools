package org.nfctools.ndef.wkt.records.handover;

import org.nfctools.ndef.wkt.records.AbstractWellKnownRecord;

/**
 * 
 * The Error Record is used in the Handover Select Record to indicate that the Handover Selector failed 
 * to successfully process the most recently received Handover Request Message. It SHALL NOT be used elsewhere.
 * 
 * @author Thomas Rorvik Skjolberg (skjolber@gmail.com)
 *
 */

public class ErrorRecord extends AbstractWellKnownRecord {

	public static final byte[] TYPE = {0x65, 0x72, 0x72}; // "err"

	/** An 8-bit field that indicates the specific type of error that caused the Handover Selector to return the Error Record */
	
	public static enum ErrorReason {
		/** The Handover Request Message could not be processed due to temporary memory constraints. 
		 * Resending the unmodified Handover Request Message might be successful after a time interval 
		 * of at least the number of milliseconds expressed in the error data field.
		 */
		
		TemporaryMemoryConstraints((byte)0x01), 
		/** 
		 * The Handover Request Message could not be processed due to permanent memory constraints. 
		 * Resending the unmodified Handover Request Message will always yield the same error condition. 
		 */
		PermanenteMemoryConstraints((byte)0x02),
		/** 
		 * The Handover Request Message could not be processed due to carrier-specific constraints. 
		 * Resending the Handover Request Message might not be successful until after a time interval 
		 * of at least the number of milliseconds expressed in the error data field. 
		 */
		CarrierSpecificConstraints((byte)0x03);
		
		private ErrorReason(byte value) {
			this.value = value;
		}
		
		private byte value;
		
		public byte getValue() {
			return value;
		}
		
	}
	public ErrorRecord() {
		super(TYPE);
	}

	/** This 16-bit field contains an integer number that SHALL be randomly generated before sending a Handover Request Message */
	private ErrorReason errorReason;

	/** A sequence of octets providing additional information about the conditions that caused the handover 
	 * selector to enter erroneous state. The syntax and semantics of this data are determined by the ERROR_REASON 
	 * field and are specified in Table 4. The number of octets encoded in the ERROR_DATA field SHALL be 
	 * determined by the number of octets in the payload of the Error Record minus 1.
	 */
	
	private long errorData;

	public ErrorReason getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(ErrorReason errorReason) {
		this.errorReason = errorReason;
	}

	public long getErrorData() {
		return errorData;
	}

	public void setErrorData(long errorData) {
		this.errorData = errorData;
	}


	
}

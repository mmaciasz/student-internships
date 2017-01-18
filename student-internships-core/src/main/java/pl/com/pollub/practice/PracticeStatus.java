package pl.com.pollub.practice;

/**
 * Created by Maciek on 2016-10-23.
 */
public enum PracticeStatus {

    /** Rozpoczęta */
    ACTIVE,

    /** Zakończona */
    ENDED,

    /** Usunięta */
    DELETED,
    /** Waiting for approval by firm eployee */
    WAITING_FOR_APPROVAL,

    /** Approved by firm employee */
    APPROVED,

    /** Rejected by firm employee */
    REJECTED,

    /** Cancelled by firm employee or student */
    CANCELLED,

    /** Practice was ended */
    COMPLETED
    ;

}

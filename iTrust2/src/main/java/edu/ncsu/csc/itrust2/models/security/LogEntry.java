package edu.ncsu.csc.itrust2.models.security;

import edu.ncsu.csc.itrust2.adapters.ZonedDateTimeAdapter;
import edu.ncsu.csc.itrust2.adapters.ZonedDateTimeAttributeConverter;
import edu.ncsu.csc.itrust2.models.DomainObject;
import edu.ncsu.csc.itrust2.models.enums.TransactionType;

import java.time.ZonedDateTime;
import javax.persistence.Basic;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.google.gson.annotations.JsonAdapter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class that represents a LogEntry that is created in response to certain user actions. Contains a
 * required TransactionType code (specifying the event that happened), a username, and a time when
 * the event occurred. Has support for an optional secondary user and message for further
 * elaboration
 *
 * @author Kai Presler-Marshall
 */
@Schema(description = "이벤트에 관한 로그 기록입니다.")
@NoArgsConstructor
@Getter
@Entity
@Table(name = "log_entry")
public class LogEntry extends DomainObject {

    /** Type of event that has been logged */
    @Schema(description = "로그로 기록된 이벤트의 유형입니다.")
    @Setter
    @NotNull private TransactionType logCode;

    /** The primary user for the event that has been logged */
    @Schema(description = "이벤트의 주요 사용자입니다.")
    @Setter
    @NotNull private String primaryUser;

    /** The timestamp of when the event occurred */
    @Schema(description = "이벤트 발생 시간입니다.")
    @Setter
    @NotNull @Basic
    // Allows the field to show up nicely in the database
    @Convert(converter = ZonedDateTimeAttributeConverter.class)
    @JsonAdapter(ZonedDateTimeAdapter.class)
    private ZonedDateTime time;

    /** The secondary user for the event that has been logged (optional) */
    @Schema(description = "이벤트의 보조 사용자입니다.")
    private String secondaryUser;

    /** An additional elaborative message for the event that has been logged. Optional. */
    @Schema(description = "로그에 대한 설명 메세지입니다.")
    @Setter
    private String message;

    /** ID of the LogEntry */
    @Schema(description = "고유 아이디입니다.")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Create a LogEntry from the most complete set of information.
     *
     * @param code The type of event that occurred and will be logged.
     * @param primaryUser The primary user that triggered the event
     * @param secondaryUser The secondary user involved
     * @param message An optional message for the event
     */
    public LogEntry(
            final TransactionType code,
            final String primaryUser,
            final String secondaryUser,
            final String message) {
        this.setLogCode(code);
        this.setPrimaryUser(primaryUser);
        this.setSecondaryUser(secondaryUser);
        this.setMessage(message);
        this.setTime(ZonedDateTime.now());
    }

    /**
     * Sets the SecondaryUser on the Log Entry
     *
     * @param secondaryUser Optional secondary user for the LogEntry
     */
    public void setSecondaryUser(final String secondaryUser) {
        if (!this.getPrimaryUser().equals(secondaryUser)) {
            this.secondaryUser = secondaryUser;
        }
    }
}

package edu.ncsu.csc.itrust2.models;

import edu.ncsu.csc.itrust2.models.enums.HouseholdSmokingStatus;
import edu.ncsu.csc.itrust2.models.enums.PatientSmokingStatus;

import java.util.regex.Pattern;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Object persisted in the database that represents the BasicHealthMetrics of a patient's office
 * visit.
 *
 * @author Matthew Gray
 * @author Kai Presler-Marshall
 */
@Schema(description = "환자의 기본적인 건강 정보입니다.")
@NoArgsConstructor
@Getter
@Entity
@Table(name = "basic_health_metrics")
public class BasicHealthMetrics extends DomainObject {

    /** ID of the AppointmentRequest */
    @Schema(description = "고유 아이디입니다.")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Height or length of the person. Up to a 3-digit number and potentially one digit of decimal
     * precision. > 0
     */
    @Schema(description = "환자의 신장입니다.")
    private Float height;

    /**
     * Weight of the person. Up to a 3-digit number and potentially one digit of decimal precision.
     * > 0
     */
    @Schema(description = "환자의 몸무게입니다.")
    private Float weight;

    /**
     * Head circumference of the person. Up to a 3-digit number and potentially one digit of decimal
     * precision. > 0
     */
    @Schema(description = "환자의 머리 둘레입니다.")
    private Float headCircumference;

    /** Systolic blood pressure. 3-digit positive number. */
    @Schema(description = "수축기 혈압입니다.")
    private Integer systolic;

    /** Diastolic blood pressure. 3-digit positive number. */
    @Schema(description = "확장기 혈압입니다.")
    private Integer diastolic;

    /** HDL cholesterol. Between 0 and 90 inclusive. */
    @Schema(description = "HDL 콜레스테롤 수치입니다.")
    private Integer hdl;

    /** LDL cholesterol. Between 0 and 600 inclusive. */
    @Schema(description = "LDL 콜레스테롤 수치입니다.")
    private Integer ldl;

    /** Triglycerides cholesterol. Between 100 and 600 inclusive. */
    @Schema(description = "Triglycerides 콜레스테롤 수치입니다.")
    private Integer tri;

    /** Smoking status of the patient's household. */
    @Schema(description = "가정 내 흡연 상태입니다.")
    @Setter
    private HouseholdSmokingStatus houseSmokingStatus;

    /** Smoking status of the patient. */
    @Schema(description = "환자 흡연 상태입니다.")
    @Setter
    private PatientSmokingStatus patientSmokingStatus;

    /** The Patient who is associated with this AppointmentRequest */
    @Schema(description = "본 요청과 연관된 환자입니다.")
    @Setter
    @NotNull @ManyToOne
    @JoinColumn(name = "patient_id", columnDefinition = "varchar(100)")
    private User patient;

    /** The HCP who is associated with this AppointmentRequest */
    @Schema(description = "본 요청과 연관된 의료진입니다.")
    @Setter
    @NotNull @ManyToOne
    @JoinColumn(name = "hcp_id", columnDefinition = "varchar(100)")
    private User hcp;

    /**
     * Sets the height
     *
     * @param height the height to set
     */
    public void setHeight(final Float height) {
        if (height == null) {
            return;
        }

        if (!Pattern.matches("^[0-9]{1,3}(\\.[0-9]?)?$", String.valueOf(height))) {
            throw new IllegalArgumentException(
                    "Height cannot be less than .1 or greater than 999.9");
        }
        this.height = height;
    }

    /**
     * Sets the weight
     *
     * @param weight the weight to set, min .1, max 999.9
     */
    public void setWeight(final Float weight) {
        if (weight == null) {
            return;
        }
        if (!Pattern.matches("^[0-9]{1,3}(\\.[0-9]?)?$", String.valueOf(weight))) {
            throw new IllegalArgumentException(
                    "Weight cannot be less than .1 or greater than 999.9");
        }
        this.weight = weight;
    }

    /**
     * Sets the headCircumference
     *
     * @param headCircumference the headCircumference to set
     */
    public void setHeadCircumference(final Float headCircumference) {
        if (headCircumference == null) {
            return;
        }
        if (!Pattern.matches("^[0-9]{1,3}(\\.[0-9]?)?$", String.valueOf(headCircumference))) {
            throw new IllegalArgumentException(
                    "Head circumference cannot be less than .1 or greater than 999.9");
        }
        this.headCircumference = headCircumference;
    }

    /**
     * Sets the diastolic blood pressure
     *
     * @param diastolic the diastolic to set
     */
    public void setDiastolic(final Integer diastolic) {
        if (diastolic == null) {
            return;
        }
        if (diastolic < 0 || diastolic > 999) {
            throw new IllegalArgumentException("Diastolic must be a 3 digit positive number.");
        }
        this.diastolic = diastolic;
    }

    /**
     * Sets the systolic blood pressure
     *
     * @param systolic the systolic to set
     */
    public void setSystolic(final Integer systolic) {
        if (systolic == null) {
            return;
        }
        if (systolic < 0 || systolic > 999) {
            throw new IllegalArgumentException("Systolic must be a 3 digit positive number.");
        }
        this.systolic = systolic;
    }

    /**
     * Sets HDL cholesterol. Between 0 and 90 inclusive.
     *
     * @param hdl the hdl to set
     */
    public void setHdl(final Integer hdl) {
        if (hdl == null) {
            return;
        }
        if (hdl < 0 || hdl > 90) {
            throw new IllegalArgumentException("HDL must be between 0 and 90 inclusive.");
        }
        this.hdl = hdl;
    }

    /**
     * Sets LDL cholesterol. Between 0 and 600 inclusive.
     *
     * @param ldl the ldl to set
     */
    public void setLdl(final Integer ldl) {
        if (ldl == null) {
            return;
        }
        if (ldl < 0 || ldl > 600) {
            throw new IllegalArgumentException("LDL must be between 0 and 600 inclusive.");
        }
        this.ldl = ldl;
    }

    /**
     * Sets triglycerides cholesterol. Between 100 and 600 inclusive.
     *
     * @param tri the tri to set
     */
    public void setTri(final Integer tri) {
        if (tri == null) {
            return;
        }
        if (tri < 100 || tri > 600) {
            throw new IllegalArgumentException(
                    "Triglycerides must be between 100 and 600 inclusive.");
        }
        this.tri = tri;
    }

    /**
     * Validates an office visit form for containing correct fields for patients 12 and over.
     * Expects the basic health metrics to already be set by the OfficeVisit constructor.
     */
    public void validate12AndOver() {
        // should already be set in office visit constructor
        if (getDiastolic() == null
                || getHdl() == null
                || getHeight() == null
                || getHouseSmokingStatus() == null
                || getLdl() == null
                || getPatientSmokingStatus() == null
                || getSystolic() == null
                || getTri() == null
                || getWeight() == null) {
            throw new IllegalArgumentException(
                    "Not all necessary fields for basic health metrics were submitted.");
        }
    }

    /** Validates an office visit form for containing correct fields for patients 12 and under. */
    public void validateUnder12() {
        // should already be set in office visit constructor
        if (getDiastolic() == null
                || getHeight() == null
                || getHouseSmokingStatus() == null
                || getSystolic() == null
                || getWeight() == null) {
            throw new IllegalArgumentException(
                    "Not all necessary fields for basic health metrics were submitted.");
        }
    }

    /** Validates an office visit form for containing correct fields for patients 3 and under. */
    public void validateUnder3() {
        // should already be set in office visit constructor
        if (getHeight() == null
                || getHeadCircumference() == null
                || getHouseSmokingStatus() == null
                || getWeight() == null) {
            throw new IllegalArgumentException(
                    "Not all necessary fields for basic health metrics were submitted.");
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((diastolic == null) ? 0 : diastolic.hashCode());
        result = prime * result + ((hcp == null) ? 0 : hcp.hashCode());
        result = prime * result + ((hdl == null) ? 0 : hdl.hashCode());
        result = prime * result + ((headCircumference == null) ? 0 : headCircumference.hashCode());
        result = prime * result + ((height == null) ? 0 : height.hashCode());
        result =
                prime * result + ((houseSmokingStatus == null) ? 0 : houseSmokingStatus.hashCode());
        result = prime * result + ((ldl == null) ? 0 : ldl.hashCode());
        result = prime * result + ((patient == null) ? 0 : patient.hashCode());
        result =
                prime * result
                        + ((patientSmokingStatus == null) ? 0 : patientSmokingStatus.hashCode());
        result = prime * result + ((systolic == null) ? 0 : systolic.hashCode());
        result = prime * result + ((tri == null) ? 0 : tri.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BasicHealthMetrics other = (BasicHealthMetrics) obj;
        if (diastolic == null) {
            if (other.diastolic != null) {
                return false;
            }
        } else if (!diastolic.equals(other.diastolic)) {
            return false;
        }
        if (hcp == null) {
            if (other.hcp != null) {
                return false;
            }
        } else if (!hcp.equals(other.hcp)) {
            return false;
        }
        if (hdl == null) {
            if (other.hdl != null) {
                return false;
            }
        } else if (!hdl.equals(other.hdl)) {
            return false;
        }
        if (headCircumference == null) {
            if (other.headCircumference != null) {
                return false;
            }
        } else if (!headCircumference.equals(other.headCircumference)) {
            return false;
        }
        if (height == null) {
            if (other.height != null) {
                return false;
            }
        } else if (!height.equals(other.height)) {
            return false;
        }
        if (houseSmokingStatus != other.houseSmokingStatus) {
            return false;
        }
        if (ldl == null) {
            if (other.ldl != null) {
                return false;
            }
        } else if (!ldl.equals(other.ldl)) {
            return false;
        }
        if (patient == null) {
            if (other.patient != null) {
                return false;
            }
        } else if (!patient.equals(other.patient)) {
            return false;
        }
        if (patientSmokingStatus != other.patientSmokingStatus) {
            return false;
        }
        if (systolic == null) {
            if (other.systolic != null) {
                return false;
            }
        } else if (!systolic.equals(other.systolic)) {
            return false;
        }
        if (tri == null) {
            if (other.tri != null) {
                return false;
            }
        } else if (!tri.equals(other.tri)) {
            return false;
        }
        if (weight == null) {
            return other.weight == null;
        } else return weight.equals(other.weight);
    }
}

package ru.tehnobear.essence.dao.entries;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLJoinTableRestriction;
import ru.tehnobear.essence.dao.dto.Audit;

import java.io.Serializable;
import java.util.UUID;


/**
 * The persistent class for the "t_report_asset" database table.
 * 
 */
@Entity
@Table(name="\"t_report_asset\"")
@NamedQuery(name="TReportAsset.findAll", query="SELECT t FROM TReportAsset t")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class TReportAsset extends Audit implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String formatPostgres =
			"INSERT INTO ${user.table}.t_report_asset\n" +
					"(ck_id, cv_name, ck_asset, ck_report, ck_user, ct_change, ct_create, ck_report_format, cl_deleted)\n" +
					"VALUES(%s::uuid, %s, %s::uuid, %s::uuid, %s, %s::timestamp, %s::timestamp, %s::uuid, %s)\n" +
					"ON CONFLICT (ck_report, cv_name)\n" +
					"DO UPDATE \n" +
					"SET ck_id=EXCLUDED.ck_id, ck_asset=EXCLUDED.ck_asset, ck_user=EXCLUDED.ck_user, ct_change=EXCLUDED.ct_change, ct_create=EXCLUDED.ct_create, ck_report_format=EXCLUDED.ck_report_format, cl_deleted=EXCLUDED.cl_deleted;\n";
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column(name="\"ck_id\"", nullable = false)
	private UUID ckId;

	@Column(name="\"cv_name\"", nullable = false)
	private String cvName;

	//bi-directional many-to-one association to TAsset
	@ManyToOne
	@JoinColumn(name="\"ck_asset\"", nullable = false)
	@SQLJoinTableRestriction("not clDeleted")
	private TAsset asset;

	//bi-directional many-to-one association to TReport
	@ManyToOne
	@JoinColumn(name="\"ck_report\"", nullable = false)
	@SQLJoinTableRestriction("not clDeleted")
	private TReport report;

	//bi-directional many-to-one association to TReport
	@ManyToOne
	@JoinColumn(name="\"ck_report_format\"")
	@SQLJoinTableRestriction("not clDeleted")
	private TReportFormat format;

	@Override
	public String toPostgresPatch() {
		return String.format(
				formatPostgres,
				toStringPostgres(ckId),
				toStringPostgres(cvName),
				toStringPostgres(asset == null ? null : asset.getCkId()),
				toStringPostgres(report == null ? null : report.getCkId()),
				toStringPostgres(getCkUser()),
				toStringPostgres(getCtChange()),
				toStringPostgres(getCtCreate()),
				toStringPostgres(format == null ? null : format.getCkId()),
				toStringPostgres(isClDeleted())
		);
	}
}
--liquibase formatted sql
--changeset patcher-core:ERJGetDbPatch dbms:postgresql runOnChange:true splitStatements:false stripComments:false
INSERT INTO s_mt.t_query (ck_id, ck_provider, ck_user, ct_change, cr_type, cr_access, cn_action, cv_description, cc_query)
 VALUES('ERJGetDbPatch', 'er_api', '4fd05ca9-3a9e-4d66-82df-886dfa082113', '2024-09-23T19:39:24.653+0300', 'dml', 'po_session', null, 'Список снятых патчей',
 '
v = {
    method: ''POST'',
    url: `${jt_provider_params.defaultGateUrl}/admin/DbPatch?session=${jt_in_param.sess_session}`,
    json: { 
        data: jt_in_param.json.filter,
        filter: jt_in_param.json.filter.jl_filter,
        sort: jt_in_param.json.filter.jl_sort,
        fetch: jt_in_param.json.filter.jn_fetch,
        offset: jt_in_param.json.filter.jn_offset
    },
    resultParse: "jt_result.success?jt_result.data.map((val)=>Object.assign(val,{jn_total_cnt:jt_result.total,cctParameter:null})):{jt_message:jt_result.message}",
}'
) on conflict (ck_id) do update set cc_query = excluded.cc_query, ck_provider = excluded.ck_provider, ck_user = excluded.ck_user, ct_change = excluded.ct_change, cr_type = excluded.cr_type, cr_access = excluded.cr_access, cn_action = excluded.cn_action, cv_description = excluded.cv_description;

SELECT
    dt AS '日期',
    type AS '来源',
    dau AS 'DAU',
    poi AS '到店人数',
    access AS '触达用户',
    conn AS '使用人数',
    succ AS '连接成功人数',
    (poi/dau) * 100 AS '到店占比',
    access/poi AS '触达率',
    conn/access AS '使用率',
    succ/conn AS '成功率',
    unopen_succ/unopen AS 'unopen成功率',
    haspwd_succ/haspwd AS '有密码成功率',
    open_check_succ/open_check AS 'oc成功率',
    open_uncheck_succ/open_uncheck AS 'ou成功率',
    open_check AS '用oc网人数',
    open_check_succ AS '用oc网成功人数',
    open_uncheck AS '用ou网人数',
    open_uncheck_succ AS '用ou网成功人数',
    open_unknow AS '用oun网人数',
    open_unknow_succ AS '用oun网成功人数',
    unopen AS '密码网使用人数',
    unopen_succ AS '密码网成功人数',
    haspwd AS '有密码连接人数',
    haspwd_succ AS '有密码连接成功人数'
FROM
    report_intelli_wificonn_daily
WHERE
    dt BETWEEN '$begindatekey' AND '$enddatekey'
    AND
    (type = '$type' or '$type' = '0')
    AND
    type != ''
ORDER BY
    dt,type

SELECT 
    i.dau AS dau,
    i.poi AS poi,
    a.access AS access,
    a.type AS type,
    d.conn AS conn,
    d.succ AS succ,
    d.open_check AS open_check,
    d.open_check_succ AS open_check_succ,
    d.open_uncheck AS open_uncheck,
    d.open_uncheck_succ AS open_uncheck_succ,
    d.open_unknow AS open_unknow,
    d.open_unknow_succ AS open_unknow_succ,
    d.unopen AS unopen,
    d.unopen_succ AS unopen_succ,
    d.haspwd AS haspwd,
    d.haspwd_succ AS haspwd_succ
FROM
(
    SELECT
        COUNT(DISTINCT uuid) AS dau,
        COUNT(DISTINCT IF(length(poi_id) > 0, uuid, null)) AS poi,
        dt
    FROM
        log.mobile_search_intelli
    WHERE
        dt=20170329
) i
left join
(
    SELECT
        COUNT(DISTINCT uuid) AS access,
        type,
        dt
    FROM
        log.mobile_search_src2wifi
    WHERE 
        dt=20170329
    GROUP BY
        type,dt
        
    UNION ALL
    
    SELECT
        COUNT(DISTINCT uuid) AS access,
        'all' AS type,
        dt
    FROM
        log.mobile_search_src2wifi
    WHERE 
        dt=20170329
) a
on
a.dt = i.dt
left join
(
    SELECT
        type,
        dt,
        COUNT(DISTINCT uuid) AS conn,
        COUNT(DISTINCT if(internetstatus = 'ok', uuid, null)) AS succ,
        COUNT(DISTINCT if(wifiencrypttype = 'open' and internetneedcheck = 'yes', uuid, null)) AS open_check,
        COUNT(DISTINCT if(wifiencrypttype = 'open' and internetneedcheck = 'yes' and  internetstatus='ok', uuid, null)) AS open_check_succ,
        COUNT(DISTINCT if(wifiencrypttype = 'open' and internetneedcheck = 'no', uuid, null)) AS open_uncheck,
        COUNT(DISTINCT if(wifiencrypttype = 'open' and internetneedcheck = 'no' and  internetstatus='ok', uuid, null)) AS open_uncheck_succ,
        COUNT(DISTINCT if(wifiencrypttype = 'open' and internetneedcheck = 'unknow', uuid, null)) AS open_unknow,
        COUNT(DISTINCT if(wifiencrypttype = 'open' and internetneedcheck = 'unknow' and  internetstatus='ok', uuid, null)) AS open_unknow_succ,
        COUNT(DISTINCT if(wifiencrypttype != 'open', uuid, null)) AS unopen,
        COUNT(DISTINCT if(wifiencrypttype != 'open' and internetstatus='ok', uuid, null)) AS unopen_succ,
        COUNT(DISTINCT if(wifiencrypttype != 'open' and passwordright = 'ok' or passwordright = 'wnUnknow', uuid, null)) AS haspwd,
        COUNT(DISTINCT if((wifiencrypttype != 'open' and passwordright = 'ok' or passwordright = 'wnUnknow') and internetstatus='ok', uuid, null)) AS haspwd_succ
    FROM 
        ba_search.report_intelli_srcwifi_daily
    WHERE
        dt = 20170329
        AND
        passwordright IS NOT NULL
    GROUP BY
        type,dt
    
    UNION ALL
    
    SELECT
        'all' AS type,
        dt,
        COUNT(DISTINCT uuid) AS conn,
        COUNT(DISTINCT if(internetstatus = 'ok', uuid, null)) AS succ,
        COUNT(DISTINCT if(wifiencrypttype = 'open' and internetneedcheck = 'yes', uuid, null)) AS open_check,
        COUNT(DISTINCT if(wifiencrypttype = 'open' and internetneedcheck = 'yes' and  internetstatus='ok', uuid, null)) AS open_check_succ,
        COUNT(DISTINCT if(wifiencrypttype = 'open' and internetneedcheck = 'no', uuid, null)) AS open_uncheck,
        COUNT(DISTINCT if(wifiencrypttype = 'open' and internetneedcheck = 'no' and  internetstatus='ok', uuid, null)) AS open_uncheck_succ,
        COUNT(DISTINCT if(wifiencrypttype = 'open' and internetneedcheck = 'unknow', uuid, null)) AS open_unknow,
        COUNT(DISTINCT if(wifiencrypttype = 'open' and internetneedcheck = 'unknow' and  internetstatus='ok', uuid, null)) AS open_unknow_succ,
        COUNT(DISTINCT if(wifiencrypttype != 'open', uuid, null)) AS unopen,
        COUNT(DISTINCT if(wifiencrypttype != 'open' and internetstatus='ok', uuid, null)) AS unopen_succ,
        COUNT(DISTINCT if(wifiencrypttype != 'open' and passwordright = 'ok' or passwordright = 'wnUnknow', uuid, null)) AS haspwd,
        COUNT(DISTINCT if((wifiencrypttype != 'open' and passwordright = 'ok' or passwordright = 'wnUnknow') and internetstatus='ok', uuid, null)) AS haspwd_succ
    FROM 
        ba_search.report_intelli_srcwifi_daily
    WHERE
        dt = 20170329
        AND
        passwordright IS NOT NULL
) d
on
    a.type = d.type
AND
    a.dt = d.dt


------------------
SELECT
    COUNT(DISTINCT uuid) AS access_ct,
    type
FROM
    log.mobile_search_src2wifi
WHERE 
    dt=20170329
GROUP BY
    type
    
UNION ALL

SELECT
    COUNT(DISTINCT uuid) AS access_ct,
    'all' AS type
FROM
    log.mobile_search_src2wifi
WHERE 
    dt=20170329

-- A 触达用户数


dau,
poi,
access,
type,
conn,
succ,
open_check,
open_check_succ,
open_uncheck,
open_uncheck_succ,
open_unknow,
open_unknow_succ,
unopen,
unopen_succ,
haspwd,
haspwd_succ
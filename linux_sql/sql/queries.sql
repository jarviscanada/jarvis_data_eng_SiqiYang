SELECT cpu_number,id as host_id,total_mem FROM host_info
ORDER BY  cpu_number ASC, total_mem DESC;

SELECT info.id AS host_id,info.hostname,Date_trunc('hour', usage_1.timestamp) + cast(('5'::varchar||' min') as interval)  * round((date_part('minutes',usage_1.timestamp )/5)::int) as "time_stamp",(AVG(info.total_mem - usage_1.memory_free) / info.total_mem *100)::int as avg_used_mem_percentage
FROM host_info info inner join host_usage usage_1
on info.id = usage_1.host_id
group by info.id,info.hostname,"time_stamp"
order by info.id;
local key =  KEYS[1]
local limit = tonumber(ARGV[1])
local expire_time = ARGV[2]

local is_exists = redis.call("EXISTS", key)

if is_exists == 1 then
    if redis.call("INCR", key) > limit then
        return 1

    else
        return 0

    end

else

    redis.call("SET", key, 1)
    redis.call("EXPIRE", key, expire_time)
    return 19

end
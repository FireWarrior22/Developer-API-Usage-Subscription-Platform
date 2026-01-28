package ApiPlatform.dto.usage;
import java.time.YearMonth;

public class UsageSummaryResponse {

    private YearMonth period;
    private long used;
    private long limit;
    private boolean limitExceeded;

    public UsageSummaryResponse(
            YearMonth period,
            long used,
            long limit
    ) {
        this.period = period;
        this.used = used;
        this.limit = limit;
        this.limitExceeded = used >= limit;
    }

    public YearMonth getPeriod() {
        return period;
    }

    public long getUsed() {
        return used;
    }

    public long getLimit() {
        return limit;
    }

    public boolean isLimitExceeded() {
        return limitExceeded;
    }
}


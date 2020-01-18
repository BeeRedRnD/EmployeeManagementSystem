package signin;

public enum option
{
    Administrator, Employee;

    private option()
    {

    }

    public String value()
    {
        return name();
    }

    public static option fromvalue(String v)
    {
        return valueOf(v);
    }
}

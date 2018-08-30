public class q4 {

	public static void main(String[] args)
	{
		int upper_case = countCaps("IHaveFiveCapitalLetters");

		if (upper_case == 5)
		{
			System.out.println(":)");
		}

		else
		{
			System.out.println(":(");
		}
	}


	public static int countCaps(String aString)
	{
		int count = 0;
		for (int i = 0; i < aString.length(); i++)
		{
			if (Character.isUpperCase(aString.charAt(i)))
			{
				count++;
			}
		}
		return count;
	}
}

import java.util.Arrays;
public class Oef7
{
        public static void main(String args[])
        {
		int a[] = {12, 34, 56, 78, 123, 234, 99, 88};
		int b[] = new int[8];
		for(int k=0; k<8; k++)
		{
			int indexGrootsteGetal=0;
			int grootsteGetal=0;
			for(int i=0; i<8; i++)
			{
				if(a[i]>grootsteGetal)
				{
					grootsteGetal = a[i];
					indexGrootsteGetal = i;
				}
			}
			a[indexGrootsteGetal]=0;
			b[k]=grootsteGetal;
		}
		System.out.println(Arrays.toString(b));
        }
}


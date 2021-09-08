package com.ghani;


import java.util.Arrays;
import java.util.Collections;

class GFG
{
	static int MAX_SIZE=10;
	
	
	static void sortByRow(Integer mat[][], int n,
								boolean ascending)
	{
		for (int i = 0; i < n; i++)
		{
			if (ascending)
				Arrays.sort(mat[i]);
			else
				Arrays.sort(mat[i],Collections.reverseOrder());
		}	
	}
	
	
	static void transpose(Integer mat[][], int n)
	{
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
			{
				
				int temp = mat[i][j];
				mat[i][j] = mat[j][i];
				mat[j][i] = temp;
			}
	}
	
	
	static void sortMatRowAndColWise(Integer mat[][],
											int n)
	{
		// sort rows of mat[][]
		sortByRow(mat, n, true);
	
		// get transpose of mat[][]
		transpose(mat, n);
	
		// again sort rows of mat[][] in descending
		// order.
		sortByRow(mat, n, false);
	
		// again get transpose of mat[][]
		transpose(mat, n);
	}
	
	// function to print the matrix
	static void printMat(Integer mat[][], int n)
	{
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
				System.out.print(mat[i][j] + " ");
			System.out.println();
		}
	}
	
	// Driver code
	public static void main (String[] args)
	{
		int n = 3;
		
		Integer mat[][] = {{3, 2, 1},
						{9, 8, 7},
						{6, 5, 4}};
	
		System.out.print("Original Matrix:\n");
		printMat(mat, n);
	
		sortMatRowAndColWise(mat, n);
	
		System.out.print("\nMatrix After Sorting:\n");
		printMat(mat, n);
	}
}

// This code is contributed by Anant Agarwal.

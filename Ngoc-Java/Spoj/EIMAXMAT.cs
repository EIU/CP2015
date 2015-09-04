using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Learning
{
    class Program
    {
        static void Main(string[] args)
        {
            var T = NextInt();
            while (T-- > 0)
            {
                var N = NextInt();
                var M = NextInt();
                var matrix = new int[N, M];

                for (var i = 0; i < N; i++)
                {
                    for (var j = 0; j < M; j++)
                    {
                        matrix[i, j] = NextInt();
                    }
                }

                int maxVal = Int32.MinValue;
                int countElement = 0;

                for (var i = 0; i < N; i++)
                {
                    for (var j = 0; j < M; j++)
                    {
                        if (matrix[i, j] > maxVal)
                        {
                            maxVal = matrix[i, j];
                            countElement = 1;
                        }
                        else if (matrix[i, j] == maxVal)
                        {
                            countElement++;
                        }
                    }
                }

                Console.Out.WriteLine(maxVal + " " + countElement);
            }
            Console.ReadLine();
        }

        static int s_index = 0;
        static List<string> s_tokens;
        private static string Next()
        {
            while (s_tokens == null || s_index == s_tokens.Count)
            {
                s_tokens = Console.ReadLine().Split(' ').ToList();
                s_index = 0;
            }
            return s_tokens[s_index++];
        }

        private static int NextInt()
        {
            return Int32.Parse(Next());
        }

        private static long NextLong()
        {
            return Int64.Parse(Next());
        }
    }
}

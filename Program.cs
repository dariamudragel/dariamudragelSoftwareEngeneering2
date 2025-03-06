using System;

namespace CalculatorCSharp
{
    public class Calculator
    {
        public double Add(double a, double b) => a + b;
        public double Subtract(double a, double b) => a - b;
        public double Multiply(double a, double b) => a * b;
        public double Divide(double a, double b)
        {
            if (b == 0)
                throw new DivideByZeroException("Cannot divide by zero.");
            return a / b;
        }
    }

    class Program
    {
        static void Main()
        {
            Calculator calc = new Calculator();
            Console.WriteLine("Enter first number:");
            double num1 = Convert.ToDouble(Console.ReadLine());
            Console.WriteLine("Enter second number:");
            double num2 = Convert.ToDouble(Console.ReadLine());

            Console.WriteLine("Addition: " + calc.Add(num1, num2));
            Console.WriteLine("Subtraction: " + calc.Subtract(num1, num2));
            Console.WriteLine("Multiplication: " + calc.Multiply(num1, num2));
            Console.WriteLine("Division: " + (num2 != 0 ? calc.Divide(num1, num2).ToString() : "Cannot divide by zero"));

            RunTests();
        }

        static void RunTests()
        {
            Calculator calc = new Calculator();
            Console.WriteLine("Running tests...");
            Console.WriteLine(calc.Add(10, 5) == 15 ? "Addition test passed" : "Addition test failed");
            Console.WriteLine(calc.Subtract(10, 5) == 5 ? "Subtraction test passed" : "Subtraction test failed");
            Console.WriteLine(calc.Multiply(10, 5) == 50 ? "Multiplication test passed" : "Multiplication test failed");
            Console.WriteLine(calc.Divide(10, 5) == 2 ? "Division test passed" : "Division test failed");
            try
            {
                calc.Divide(10, 0);
                Console.WriteLine("Division by zero test failed");
            }
            catch (DivideByZeroException)
            {
                Console.WriteLine("Division by zero test passed");
            }
        }
    }
}

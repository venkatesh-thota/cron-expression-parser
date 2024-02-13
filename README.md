# cron-expression-parser

This tool operates via the command line interface and is engineered to dissect a provided cron expression. It elaborates on each component within the cron expression to unveil the corresponding moments when the specified command will execute.

The utility strictly conforms to the standardized cron layout, comprising five temporal facets (minute, hour, day of the month, month, and day of the week), alongside the associated directive. Input is anticipated as a single line, with the cron expression being the solitary argument furnished to the program.

Below illustrates how to employ the tool:

Copy code
$ your-program "*/15 0 1,15 * 1-5 /usr/bin/find"
# Instructions for Use
To activate the tool, pursue these steps:

Execute the shell script titled build_and_run.sh whilst supplying the cron expression as a parameter, akin to the illustration below:

shell
Copy code
$ ./build_and_run.sh "*/15 0 1,15 * 1-5 /usr/bin/find"
This script compiles the java files into the ./src/out directory and initiates the execution of the primary class from there.

Output materializes in tabular form, with the field designation occupying the initial 14 columns, succeeded by a series of times, separated by spaces.

For instance, with the given input:

./build_and_run.sh "*/15 0 1,15 * 1-5 /usr/bin/find"

The tool will yield the ensuing output:

sql
Copy code
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
# Constraints
The tool lacks support for specialized time descriptors such as "@yearly". Additionally, it overlooks months with fewer than 31 days.

# Testing Suite
An array of tests resides within the test/ directory. While not exhaustive, these tests encompass diverse parsing scenarios, guaranteeing the desired output aligns with the given expressions.






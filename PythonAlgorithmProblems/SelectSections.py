

def select_sections(occupancy_probability: list[list[int]]):
    """
    ---Function---
    :param occupancy_probability: a 2D list of integers representing an office 
        floor plan with the probability of each square being occupied
    :return: a list of integers representing the optimal squares from top to 
        bottom one from each row and adjecent to each other with the lowest total probability of being occupied and the value for that probability

    ---Thought notes---
    i won't lie i saw this as a a gift from god after spending days on the last task this was a breath of nice clean unpolluted air. i have two words,
    DYNAMIC PROGRAMMING, my favourite part of dynamic programming is that you dont need recursion (or at least for this task) and you can just easily walk your way up from the bottom to the top, or in this case from the top to the botom "ha ha ha" - the joker. as for the algorythm, the idea is to create aother new array the same size as the input and duplicate the top row as a starting point. for every other squares in the array going left to right top to bottom set the value as the minimum of the three values above it plus the value of the current squares from the input array, this is because in the new array the current squares is empty (if the squares is on the edge of either side of the array the top left/right is treated as infinity). once this is done we find the minimum value at the bottom row and trace our way up until we reach the top row, returning this route with its total probability.

    ---Recursion Formula---

                            [i,j]                               if i = 0
    minSquarePath(i,j) = {  [i,j] + min([i-1,j],[i-1,j+1])      if j = 0
    n = number of columns   [i,j] + min([i-1,j-1],[i-1,j])      if j = n-1 
                            [i,j] + min([i-1,j-1],[i-1,j],[i-1,j+1]) otherwise

     ---Approach description---
     1. create a new array the same size as the input array
        1.1 create a new empty 2D array size of input array               #O(nm)
        1.2 link the top row of the input array to the new array           #O(1)

        1.Total Time Complexity for: O(nm)
        1.Total Aux Space Complexity: O(nm)

     2. work our way through the array for every square                   #O(nm)
        2.1 find if square on the edge of the array                        #O(1)
        2.2 find minimum of above adjecent squares                         #O(1)
        2.3 set value of current square                                    #O(1)

        2.Total Time Complexity for: O(nm)
        2.Total Aux Space Complexity: O(1)

     3. trace our path back up the array 
        3.1 create result list                                             #O(n)
        3.2 find minimum value in the bottom row                           #O(m)
        3.3 trace our way back up the array                                #O(n)

        3.Total Time Complexity for: O(n + m)
        3.Total Aux Space Complexity: O(n)

    Total Time Complexity for: O(nm)
    Total Aux Space Complexity: O(nm)

    """
    # (1) create a new array the same size as the input array
    height = len(occupancy_probability)
    if height == 0:
        return [0, []]
    width = len(occupancy_probability[0])
    result_table = a = [[0] * width for i in range(height)]
    result_table[0] = occupancy_probability[0]

    # (2) work our way through the array for every square
    for n in range(1, height):
        for m in range(width):
            # (2.1) find if square on the edge of the array
            if (m < width - 1):
                top_right_value = result_table[n - 1][m + 1]
            else:
                top_right_value = float('inf')
            if m != 0:
                top_left_value = result_table[n - 1][m - 1]
            else:
                top_left_value = float('inf')
            # (2.2) find minimum of above adjecent squares
            min_above_value = min(
                top_left_value, result_table[n - 1][m], top_right_value)
            # (2.3) set value of current square
            result = (occupancy_probability[n][m] + min_above_value)
            result_table[n][m] = result
    # (3) trace our path back up the array
    # (3.1) create result list
    result = [None] * height
    # (3.2) find minimum value in the bottom row
    min_value = min(result_table[height - 1])
    m = result_table[height - 1].index(min_value)  # min_width_index
    # set the last value in the result list to min buttom row
    result[height-1] = (height-1, m)

    # (3.3) trace our way back up the array
    for n in range(height-1, 0, -1):
        above_value = result_table[n][m] - occupancy_probability[n][m]
        # theres three different ways we couldve arrived to this square
        # either directly from above or from the above left or above right
        if above_value == result_table[n-1][m]:
            result[n-1] = ((n-1, m))
        elif above_value == result_table[n-1][m-1]:
            m -= 1
            result[n-1] = ((n-1, m))
        else:
            m += 1
            result[n-1] = ((n-1, m))
    # return the path we took and the total probability
    return [min_value, result]

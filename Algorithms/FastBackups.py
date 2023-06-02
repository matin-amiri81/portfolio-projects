
'''
    ---Function---
    :param connections: a list of tuples representing the edge between nodes
    :param maxIn: a list of integers representing a nodes maximum incoming flow 
    :param maxOut: a list of integers representing a nodes maximum outgoing flow
    :param origin: the source node
    :param targets: a list of target nodes

    :return: the maximum throughput from origin to targets

    ---Thought Notes---
    fist sight the problem was clearly max flow but it had some sight adjustments, mainly the max incoming flow and outgoing flow of each nodes. the way i went about it was to create a two node system where each original node is only connected to the incoming edges and then is connected to a new node that has all the outgoing edges. the weight of this new edge is the minimum of the maxIn and maxOut of the original node unless the node is a target node then the weight is the maxIn of the original node since the target node will not need to output any flow, or a source node where the weight is the maxOut of the original node since the source node will not have any incoming flow. once this is done the graph is ready to be run through the ford fulkerson algorithm and return max flow of the network. using a vertex matrix graph is more simple and easy to code but running bfs on a matrix would have V^2 complexity so i used an adjecency list to only loop through paths that exist but to do this we have to create both in this function to pass to ford fulkerson. 

    ---Approach Description---
    1. assign num_nodes and adjecency list
        1.1 find the number of nodes in the graph                        O(1)
        1.2 create a super target node                                   O(1)
        1.3 create an adjecency list of size 2 * (number of nodes + 1)   O(V)
        
        1.Total Time Complexity for: O(V)
        1.Total Aux Space Complexity: O(V)

    2. add nodes to adjecency list
        2.1 add original nodes and paths to adjecency                    O(E)
        2.2 add second nodes and connecting paths to original            O(V)
        2.3 add origin and paths to second node                          O(1)
        2.3 overide paths from target to only go super target            O(V)
    
        2.Total Time Complexity for: O(E)
        2.Total Aux Space Complexity: O(E)

    3. create the 2d matrix graph
        2.1 inicialise graph                                           O(V^2)
        2.2 add every path from adjacency list to the graph              O(E)
                
        3.Total Time Complexity for: O(V^2)
        3.Total Aux Space Complexity: O(V^2)

    4. return the max flow of the graph using ford fulkerson          O(EV^2)

        4.Total Time Complexity for: O(EV^2)
        4.Total Aux Space Complexity: O(V^2)

        
    Total Time Complexity for: O(EV^2)
    Total Aux Space Complexity: O(V^2)
'''


def maxThroughput(
        connections: list[tuple], maxIn: list[int], maxOut: list[int], origin: int, targets: list[int]):

    # Find the number of nodes
    num_nodes = len(maxIn)

    # inicialise the adjecency list
    super_target = (num_nodes+1)*2
    adjecency_list = [[] for _ in range(super_target+1)]

    # add the connections to the adjecency list
    for connection in connections:
        # (start,end,weight)
        # creating a two node system to implement maxin/maxout
        # connecting the second node to the destination
        start = connection[0]
        startLink = start + num_nodes+1
        end = connection[1]
        adjecency_list[startLink].append((startLink, end, connection[2]))

    # connecting the the first nodes to second nodes
    for i in range(1, len(maxIn)):
        adjecency_list[i].append((i, i+num_nodes+1, min(maxIn[i], maxOut[i])))

    # add the origin and paths to second node
    adjecency_list[origin].append(
        (origin, origin + num_nodes+1, maxOut[origin]))

    # add the super target and paths to super target to connections
    for target in targets:
        # overide target nodes to only have links to super target
        adjecency_list[target] = [(target, target+num_nodes+1, maxIn[target])]
        adjecency_list[target+num_nodes+1].append(
            (target+num_nodes+1, super_target, float('inf')))

    # create the proper 2d graph
    graph = [[0 for _ in range(super_target+1)]for _ in range(super_target+1)]
    for nodes in adjecency_list:
        for paths in nodes:
            graph[paths[0]][paths[1]] = paths[2]

    return Ford_Fulkerson(graph, origin, super_target, adjecency_list)


'''
    ---Function---
    :param graph: the graph to be searched a v*v matrix with the weights as 
        values
    :param source: the source node
    :param target: the target node
    :param parent_list: the list of parents

    :return: true if there is a path from source to target, false otherwise

    ---Thought Notes---
    coming into this problem i inicially thought of returning the parent list to be used in the ford fulkerson algorithm, but i realised if the parent list was given to the function and altered and insead a boolean was returned representing if the there is a path to the target then this function could also be used to loop through as once there is no augmenting path the ford fulkerson algorithm is done. second problem came when i finished the BFS and everything worked i noticed that you cant simply run through the entire graph to find the augmenting path as the complexity would be V^2 instead of E. so i decided to use the adjecency list to only loop through paths that exist.

    ---Approach Description---
    1. Inicialise the visited, queue and add the source
        1.1 create a visited list of size number of nodes               O(V)
        1.2 create a queue and add the source to queue and visited      O(1)
        
        1.Total Time Complexity for: O(V)
        1.Total Aux Space Complexity: O(V)

    2. Find path from source to target
        2.1 loop through the queue                                      O(E)
        2.2 check if node is visited                                    O(1)
        2.3 update the queue, visited and parent list                   O(1)
        2.4 return true if path exists false otherwise                  O(1)
        
        2.Total Time Complexity for: O(E)                               
        2.Total Aux Space Complexity: O(1)

    Total Time Complexity: O(E)
    Total Aux Space Complexity: O(V)
'''


def BFSrf(graph, source, target, parent_list, adjacency_list):
    # inicialise the visited list the queue and add source
    visited = [False]*(len(graph))
    queue = []
    queue.append(source)
    visited[source] = True

    while queue:
        here = queue.pop(0)
        # using the adjecency list because if we looped through the entire graph the complexity would be increased to V^2 instead of E
        for path in adjacency_list[here]:
            destination = path[1]
            if visited[destination] == False and graph[here][destination] > 0:
                # regular bfs updating the parent_list that was given
                queue.append(destination)
                visited[destination] = True
                parent_list[destination] = here
                if destination == target:
                    return True

    # We didn't reach target
    return False


'''
    ---Function---
    :param graph: the graph to be searched a v*v matrix with weights as values
    :param source: the source node
    :param target: the target node
    :param parent_list: the list parent of each index as its value
    :param adjacency list: a list of all paths indexed by start node

    :return: the max flow from source to target

    ---Thought Notes---
    since bfs returns a boolean if there is a path from source to target and updates the parent list i can use this function to loop through the graph until there is no augmenting path. once there is no augmenting path i can return the max flow. first i loop through all the nodes in the path to find the minimum flow. then i loop through the path again and update the graph and reverse edges.
    
     ---Approach Description---
    1. inicialise the max flow and parent list                          O(V)

        1. Total Time Complexity: O(V)
        1. Total Aux Space Complexity: O(V)

    2. while there is an augmenting path                             O(VE^2)
        2.1 find an augmenting path                                     O(V)
        2.2 find the minimum flow in the path                           O(V)
        2.3 update the graph and reverse edges                          O(V)

        2. Total Time Complexity: O(VE^2)
        2. Total Aux Space Complexity: O(1)

    3. return the max flow                                              O(1)
        3. Total Time Complexity: O(1)
        3. Total Aux Space Complexity: O(1)


    Total Time Complexity: O(VE^2)
    Total Aux Space Complexity: O(V)

'''


def Ford_Fulkerson(graph, source, target, adjacency_list):
    max_flow = 0
    parent_list = [None]*(len(graph))
    while BFSrf(graph, source, target, parent_list, adjacency_list):

        path_flow = float("Inf")
        cur = target
        while (cur != source):
            path_flow = min(path_flow, graph[parent_list[cur]][cur])
            cur = parent_list[cur]

        # Add path flow to overall flow
        max_flow += path_flow

        # update residual capacities of the edges and reverse edges
        # along the path
        current = target
        while (current != source):
            next = parent_list[current]
            graph[next][current] -= path_flow
            graph[current][next] += path_flow
            current = parent_list[current]

    return max_flow


if __name__ == '__main__':
    connections = [(0, 1, 3000), (1, 2, 2000), (1, 3, 1000),
                   (0, 3, 2000), (3, 4, 2000), (3, 2, 1000)]
    maxIn = [5000, 3000, 3000, 3000, 2000]
    maxOut = [5000, 3000, 3000, 2500, 1500]
    origin = 0
    targets = [4, 2]
    print(maxThroughput(
        connections, maxIn, maxOut, origin, targets))

    connections = [(0, 1, 30), (1, 3, 30), (1, 4, 30),
                   (0, 2, 30), (2, 4, 30)]
    maxIn = [5, 5, 5, 5, 5]
    maxOut = [1000, 1000, 1000, 1000, 1000]
    origin = 0
    targets = [3, 4]

    print(maxThroughput(
        connections, maxIn, maxOut, origin, targets))


# check if targets should only care about max in not min(maxin,maxout)

import heapq


def optimalRoute(source, target, passangers: list[int], roads):
    """
    ---Function---
    :param source: the source vertex
    :param target: the target vertex
    :param passangers: a list of vertices that contain passangers
    :param roads: a list of tuples (start, end, base, express) representing an
        edgewhere start and end are vertices, base and express are the weights of the edges, the express edge can only be accessed if you have gone through a passanger vertex first

    :return: a list of vertices representing the optimal route from source to  
        target


    ---Thought Notes---
    given the task and complexity i decided to use Dijkstra's algorithm to find the shortest path from source to target vertex, so i converted the edge list to an adjencency list and run Dijkstra, my first idea when looking at the different roads was to add a true/false value as the fist element of every llist in the adjencency list to represent if the vertex was holding a passanger or not which would have reset the Dijkstra algorithm, this obviously did not work in my one of a kind ms paint simulation because it would endlessly loop onto the second genius idea, split the graph into two graphs identical with one key difference the weights of the first graph would be the base weights, while the second would use express weights, and they would be joined only at the vertexes that hold passangers, reusing the idea of inicially setting a boolean value to the start of every list. this works because the express edges are always quicker or the same as the base weights so once you got on these roads you wouldnt need to go back to the base roads. i knew this would work as it passed the ms paint trials.

    ---Approach Description---
    1. convert the edge list into two adjencency list
        1.1 find the number of vertices in the graph                     # O(R)
        1.2 create two empty adjecency lists with first value            # O(R)
        1.3 change the first value to True if there is a passanger       # O(P)
        1.4 add the edge to the adjencency lists                         # O(R)
        1.5 combine the two adjencency list into one                     # O(L)

        1.Total Time Complexity for: O(R)
        1.Total Aux Space Complexity: O(L + R)

    2. run Dijkstra's algorithm on the combined adjencency list
        2.1 create a list of distances from source to every vertex       # O(L)
        2.2 create pred list                                             # O(L)
        2.3 set the source dist and pred to 0 and start                  # O(1)
        2.4 create a priority queue with the source vertex               # O(1)
        2.5 run main loop for dijkstra using a min heap           # O(R log(V))

        2.Total Time Complexity for: O(R log(L))
        2.Total Aux Space Complexity: O(L + R)

    3. walk backwards from target to source to return results
        3.1 select the correct target out of 2 vertex (bese, express)   # O(1)
        3.2 work backwards through pred list until source               # O(L)
        3.3 return the list of vertices                                 # O(1)

        3.Total Time Complexity for: O(L)
        3.Total Aux Space Complexity: O(V)

    Total Time Complexity for: O(R log(L))
    Total Aux Space Complexity: O(L + R)
    """

    # (1) convert the edge list into two adjencency list
    number_of_vertices = 0
    for i in range(len(roads)):
        number_of_vertices = max(number_of_vertices, roads[i][0], roads[i][1])
    number_of_vertices += 1

    adjacency_list_base = [[False,]for _ in range(number_of_vertices)]
    adjacency_list_express = [[True,]for _ in range(number_of_vertices)]

    # (1.3) change the first value to True if there is a passanger
    for i in passangers:
        adjacency_list_base[i][0] = True
    # (1.4) add the edge to the adjencency lists
    for (start, end, base, express) in roads:
        # if there is a passanger at the start of this road than it should lead to the express graph instead with the express weight
        if adjacency_list_base[start][0] == True:
            adjacency_list_base[start].append(
                (end + number_of_vertices, express))
        # otherwise split the graph as normal into two
        else:
            adjacency_list_base[start].append((end, base))
        adjacency_list_express[start].append(
            (end + number_of_vertices, express))
    # (1.5) join the two graphs back together, note: all express vertices now have index original + the number of vertices in the original graph
    adjacency_list_base.extend(adjacency_list_express)

    # (2) run Dijkstra's algorithm on the combined adjencency list
    """
    ---Function---
    :param graph: the adjencency list representing two graphs combined into one 
        throgh certain vertices
    :param start: the source vertex
    :param end: the target vertex
    :param num_vertex: the number of vertices in one of the graphs

    :return: a list of vertices representing the optimal route from source to  
        target through the two graphs but as if they were one graph

    Total Time Complexity for: O(E log(V))
    Total Aux Space Complexity: O(E + V)
    """
    def Dijkstra(graph, start, end, num_vertex):
        dist = [float('inf')] * len(graph)
        pred = [-1] * len(graph)
        pred[start] = start
        dist[start] = 0
        min_distance_heap = [(0, start)]

        while min_distance_heap:
            # MIN HEAPPPPPP
            current_distance, current_node = heapq.heappop(min_distance_heap)
            for neighbour, weight in graph[current_node][1:]:
                this_distance = current_distance + weight
                if this_distance < dist[neighbour]:
                    dist[neighbour] = this_distance
                    pred[neighbour] = current_node
                    # MIN HEAPPPPPP
                    heapq.heappush(min_distance_heap,
                                   (this_distance, neighbour))
        # (3) walk backwards from target to source to return results
        # (3.1) select the correct target out of 2 vertex (bese, express)
        if dist[end] < dist[end + num_vertex]:
            ct = end
        else:
            ct = end + num_vertex
        result = [end]
        # while this might look like output specific complexity, the maximum it can run is the number of vertices in the graph
        while ct != start:
            next_node = pred[ct]
            if next_node < num_vertex:
                result.append(next_node)
            else:
                result.append(next_node - num_vertex)
            ct = next_node
        # reverse to get nice order O(V) complexity
        result.reverse()
        return (result)

    # (2*) technically 2 is done here but would be more confusing
    return (Dijkstra(adjacency_list_base, source, target, number_of_vertices))

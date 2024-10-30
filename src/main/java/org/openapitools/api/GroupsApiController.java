package org.openapitools.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Generated;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.log4j.lf5.Log4JLogRecord;
import org.openapitools.model.Group;
import org.openapitools.model.GroupOrder;
import org.openapitools.service.GroupOrderService;
import org.openapitools.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.logging.Level;


@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-10-02T19:11:02.971027-04:00[America/Toronto]",
    comments = "Generator version: 7.8.0")
@Controller
@RequestMapping("${openapi.groupGrub.base-path:}")
public class GroupsApiController implements GroupsApi {

  private final NativeWebRequest request;
  private final GroupService groupService;
  private final GroupOrderService groupOrderService;
  private static final Logger logger = Logger.getLogger(GroupsApiController.class.getName());

  @Autowired
  public GroupsApiController(
      NativeWebRequest request, GroupService groupService, GroupOrderService groupOrderService) {
    this.request = request;
    this.groupService = groupService;
    this.groupOrderService = groupOrderService;
  }

  /**
   * Provides access to the current web request.
   *
   * @return An {@code Optional} containing the {@code NativeWebRequest} object if available, or
   *     empty if not.
   */
  @Override
  public Optional<NativeWebRequest> getRequest() {
    return Optional.ofNullable(request);
  }

  /**
   * Retrieves all groups in the system.
   *
   * @return A {@code ResponseEntity} containing a list of {@code Group} objects and an HTTP 200
   *     status if successful, or an HTTP 404 status if no groups are found.
   */
  @Override
  @GetMapping(
      value = "/groups/getAllGroups",
      produces = {"application/json"})
  public ResponseEntity<List<Group>> groupsGet() {

    List<Group> groupList = groupService.getAllGroup();
    if (groupList.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(groupList, HttpStatus.OK);
  }

  /**
   * Deletes a group by its unique ID.
   *
   * @param groupId A {@code UUID} representing the unique ID of the group.
   * @return A {@code ResponseEntity} with HTTP 204 (No Content) if the deletion is successful, or
   *     HTTP 404 (Not Found) if the group does not exist.
   */
  @Override
  @DeleteMapping(value = "/groups/{groupId}")
  public ResponseEntity<Void> groupsGroupIdDelete(@PathVariable("groupId") UUID groupId) {
    logger.info("groupsGroupIdDelete is called to get"+groupId);
    if (groupService.getGroupById(groupId) == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    groupService.deleteGroupById(groupId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Retrieves a group by its unique ID.
   *
   * @param groupId A {@code UUID} representing the unique ID of the group.
   * @return A {@code ResponseEntity} containing the {@code Group} object and an HTTP 200 status if
   *     successful, or an HTTP 404 status if the group is not found.
   */
  @Override
  @GetMapping(
      value = "/groups/{groupId}",
      produces = {"application/json"})
  public ResponseEntity<Group> groupsGroupIdGet(@PathVariable("groupId") UUID groupId) {
    Group group = groupService.getGroupById(groupId);
    if (group == null) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(group, HttpStatus.OK);
  }

  /**
   * Updates an existing group by its unique ID.
   *
   * @param groupId A {@code UUID} representing the unique ID of the group.
   * @param group A {@code Group} object containing the updated group details.
   * @return A {@code ResponseEntity} with HTTP 200 (OK) if successful, or HTTP 404 (Not Found) if
   *     the group does not exist.
   */
  @Override
  @PutMapping(
      value = "/groups/{groupId}",
      consumes = {"application/json"})
  public ResponseEntity<Void> groupsGroupIdPut(
      @PathVariable("groupId") UUID groupId, @Valid @RequestBody Group group) {
    Group oldGroup = groupService.getGroupById(groupId);
    if (oldGroup == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    groupService.updateGroupById(groupId, group);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Creates a new group in the system.
   *
   * @param group A {@code Group} object representing the group to be created.
   * @return A {@code ResponseEntity} with HTTP 201 (Created) if successful.
   */
  @PostMapping(
      value = "/groups",
      consumes = {"application/json"})
  public ResponseEntity<Void> groupsPost(@Valid @RequestBody Group group) {
    groupService.addGroup(group);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  /**
   * Deletes a group order by its unique ID within a group.
   *
   * @param groupId A {@code UUID} representing the unique ID of the group.
   * @param orderId A {@code UUID} representing the unique ID of the order.
   * @return A {@code ResponseEntity} with HTTP 204 (No Content) if successful, or HTTP 404 (Not
   *     Found) if the group or order is not found.
   */
  @Override
  @DeleteMapping(value = "/groups/{groupId}/orders/{orderId}")
  public ResponseEntity<Void> groupsGroupIdOrdersOrderIdDelete(
      @PathVariable("groupId") UUID groupId, @PathVariable("orderId") UUID orderId) {
    Boolean deleteSuccess = groupService.deleteGroupOrder(groupId, orderId);
    if (!deleteSuccess) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Retrieves all orders associated with a group by the group's unique ID.
   *
   * @param groupId A {@code UUID} representing the unique ID of the group.
   * @return A {@code ResponseEntity} containing a list of {@code GroupOrder} objects and an HTTP
   *     200 status if successful, or an HTTP 404 status if no orders are found.
   */
  @Override
  @GetMapping(
      value = "/groups/{groupId}/orders",
      produces = {"application/json"})
  public ResponseEntity<List<GroupOrder>> groupsGroupIdOrdersGet(
      @PathVariable("groupId") UUID groupId) {
    List<GroupOrder> groupOrderList = groupService.getGroupOrdersByGroupId(groupId);
    if (groupOrderList == null || groupOrderList.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(groupOrderList, HttpStatus.OK);
  }

  /**
   * Retrieves a group order by its unique ID within a group.
   *
   * @param groupId A {@code UUID} representing the unique ID of the group.
   * @param orderId A {@code UUID} representing the unique ID of the order.
   * @return A {@code ResponseEntity} containing the {@code GroupOrder} object and an HTTP 200
   *     status if successful, or an HTTP 404 status if the group or order is not found.
   */
  @Override
  @GetMapping(
      value = "/groups/{groupId}/orders/{orderId}",
      produces = {"application/json"})
  public ResponseEntity<GroupOrder> groupsGroupIdOrdersOrderIdGet(
      @PathVariable("groupId") UUID groupId, @PathVariable("orderId") UUID orderId) {
    if (!groupService.hasGroupOrder(groupId, orderId)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(groupOrderService.getGroupOrderById(orderId), HttpStatus.OK);
  }

  /**
   * Updates an existing group order by its unique ID within a group.
   *
   * @param groupId A {@code UUID} representing the unique ID of the group.
   * @param orderId A {@code UUID} representing the unique ID of the order.
   * @param groupOrder A {@code GroupOrder} object containing the updated order details.
   * @return A {@code ResponseEntity} with HTTP 200 (OK) if successful, or HTTP 400 (Bad Request) if
   *     the update fails.
   */
  @Override
  @PutMapping(
      value = "/groups/{groupId}/orders/{orderId}",
      consumes = {"application/json"})
  public ResponseEntity<Void> groupsGroupIdOrdersOrderIdPut(
      @PathVariable("groupId") UUID groupId,
      @PathVariable("orderId") UUID orderId,
      @Parameter(name = "GroupOrder", description = "", required = true) @Valid @RequestBody
          GroupOrder groupOrder) {
    if (!groupService.hasGroupOrder(groupId, orderId)) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    if (!groupOrderService.updateGroupOrder(orderId, groupOrder)) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Creates a new group order associated with a group.
   *
   * @param groupId A {@code UUID} representing the unique identifier of the group.
   * @param groupOrder A {@code GroupOrder} object representing the new group order.
   * @return A {@code ResponseEntity} with HTTP 201 (Created) if the group order is successfully
   *     created.
   */
  @Operation(
      operationId = "groupsGroupIdOrdersPost",
      summary = "Create a new group order.",
      responses = {
        @ApiResponse(responseCode = "201", description = "Group order created successfully.")
      })
  @PostMapping(
      value = "/groups/{groupId}/orders",
      consumes = {"application/json"})
  public ResponseEntity<Void> groupsGroupIdOrdersPost(
      @Parameter(
              name = "groupId",
              description = "The unique identifier of the group.",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("groupId")
          UUID groupId,
      @Parameter(name = "GroupOrder", description = "", required = true) @Valid @RequestBody
          GroupOrder groupOrder) {
    boolean success = groupService.addGroupOrder(groupId, groupOrder);
    if (!success) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}

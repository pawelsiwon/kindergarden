import React, { Component } from "react";

class ShowAllParentsTable extends Component {
  state = {};
  render() {
    return (
      <table className="table table-striped table-dark table-hover table-sm">
        <thead className="thead-dark">
          <tr>
            <th scope="col">#</th>
            <th scope="col">First name</th>
            <th scope="col">Last name</th>
            <th scope="col" />
          </tr>
        </thead>
        <tbody>
          {this.props.parents.map(parent => (
            <tr key={parent.id}>
              <th scopr="row">{parent.id}</th>
              <td>{parent.name}</td>
              <td>{parent.lastname}</td>
              <td className="float-right">
                <button
                  className="btn btn-success m-2"
                  onClick={e => {
                    this.props.goTo("/person/show/details" + parent.id);
                    console.log("/person/show/" + parent.id);
                  }}
                >
                  Details
                </button>
                <button
                  className="btn btn-primary m-2"
                  onClick={e => console.log("Edit")}
                >
                  Edit
                </button>
                <button
                  className="btn btn-warning m-2"
                  onClick={e => console.log("Delete")}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    );
  }
}

export default ShowAllParentsTable;

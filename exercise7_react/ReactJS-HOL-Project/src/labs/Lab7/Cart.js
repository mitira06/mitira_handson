import React from 'react';

class Cart extends React.Component {
  render() {
    return (
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody>
          {this.props.item.map((item) => {
            return (
              <tr key={item.itemname}>
                <td> {item.itemname} </td>
                <td> {item.price} </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    );
  }
}

export default Cart;
